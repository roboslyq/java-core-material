1、java序列化的结果文件与class文件规则不一致，不通通过反编译获取相应的结果，只能通过反序列化进行。
2、如何将对象保存为class文件呢？


时候会遇到查看当前jvm虚拟机中的Class对象,比如查看jvm内存中动态生成class.除了修改保存动态生成类文件的开关
（jdk动态代理sun.misc.ProxyGenerator.saveGeneratedFiles:
byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags)
private static final boolean saveGeneratedFiles = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"))).booleanValue();

）
我们还可以使用java -classpath "%JAVA_HOME%/lib/sa-jdi.jar" sun.jvm.hotspot.HSDB中ClassBrower,查找类名并点击Create .class File.
注:VM options:-verbose:class查看加载Class对象的过程

3、编译Java源码

4、
使用据说是jdk自带包sa-jdi.jar里的工具。
其中，不想自己搞，当然就利用下，sa-jdi.jar 里自带的的sun.jvm.hotspot.tools.jcore.ClassDump就可以把类的class内容dump到文件里。

ClassDump里可以设置两个System properties：

　　sun.jvm.hotspot.tools.jcore.filter Filter的类名
　　sun.jvm.hotspot.tools.jcore.outputDir 输出的目录

sa-jdi.jar 里有一个sun.jvm.hotspot.tools.jcore.PackageNameFilter，可以指定Dump哪些包里的类。PackageNameFilter里有一个System property可以指定过滤哪些包：sun.jvm.hotspot.tools.jcore.PackageNameFilter.pkgList。

所以可以通过这样子的命令来使用：

sudo java -classpath "$JAVA_HOME/lib/sa-jdi.jar" -Dsun.jvm.hotspot.tools.jcore.filter=sun.jvm.hotspot.tools.jcore.PackageNameFilter -Dsun.jvm.hotspot.tools.jcore.PackageNameFilter.pkgList=com.test sun.jvm.hotspot.tools.jcore.ClassDump <pid>
不过，我在windows下并没有成功过，原因是还要要求我 start SwDbgSrv.exe，搞不了。

其中sa-jdi.jar文件也不那么好找呢，不过也能找到！

所以，还不如自己动手，丰衣足食！

2. 自己重写一个记录工具，用agent attatch 到进程，然后利用Instrumentation和ClassFileTransformer就可以获取 到类的字节码了。
工具类如下：

复制代码
package com.xxx.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * 动态生成类拦截查看工具
 *
 * @date 2018/9/15
 */
public class ClazzDumpCustomAgent implements ClassFileTransformer {

    /**
     * 导出过滤表达式，此处为类名前缀， 以 -f 参数指定
     */
    private String filterStr;

    /**
     * 导出文件目录根目录, 以 -d 参数指定
     */
    private String exportBaseDir = "/tmp/";

    /**
     * 是否创建多级目录, 以 -r 参数指定
     */
    private boolean packageRecursive;

    public ClazzDumpCustomAgent(String exportBaseDir, String filterStr) {
        this(exportBaseDir, filterStr, false);
    }

    public ClazzDumpCustomAgent(String exportBaseDir, String filterStr, boolean packageRecursive) {
        if(exportBaseDir != null) {
            this.exportBaseDir = exportBaseDir;
        }
        this.packageRecursive = packageRecursive;
        this.filterStr = filterStr;
    }

    /**
     * 入口地址
     *
     * @param agentArgs agent参数
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs: " + agentArgs);
        String exportDir = null;
        String filterStr = null;
        boolean recursiveDir = false;
        if(agentArgs != null) {
            if(agentArgs.contains(";")) {
                String[] args = agentArgs.split(";");
                for (String param1 : args) {
                    String[] kv = param1.split("=");
                    if("-d".equalsIgnoreCase(kv[0])) {
                        exportDir = kv[1];
                    }
                    else if("-f".equalsIgnoreCase(kv[0])) {
                        filterStr = kv[1];
                    }
                    else if("-r".equalsIgnoreCase(kv[0])) {
                        recursiveDir = true;
                    }
                }
            }
            else {
                filterStr = agentArgs;
            }
        }
        inst.addTransformer(new ClazzDumpCustomAgent(exportDir, filterStr, recursiveDir));
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (needExportClass(className)) {
            int lastSeparatorIndex = className.lastIndexOf("/") + 1;
            String fileName = className.substring(lastSeparatorIndex) + ".class";
            String exportDir = exportBaseDir;
            if(packageRecursive) {
                exportDir += className.substring(0, lastSeparatorIndex);
            }
            exportClazzToFile(exportDir, fileName, classfileBuffer);         //"D:/server-tool/tmp/bytecode/exported/"
            System.out.println(className + " --> EXPORTED");
        }
        return classfileBuffer;
    }

    /**
     * 检测是否需要进行文件导出
     *
     * @param className class名,如 com.xx.abc.AooMock
     * @return y/n
     */
    private boolean needExportClass(String className) {
        if(filterStr != null) {
            if(className.startsWith(filterStr)) {
                return true;
            }
            else {
                return false;
            }
        }
        if (!className.startsWith("java") && !className.startsWith("sun")) {
            return true;
        }
        return false;
    }

    /**
     * 执行文件导出写入
     *
     * @param dirPath 导出目录
     * @param fileName 导出文件名
     * @param data 字节流
     */
    private void exportClazzToFile(String dirPath, String fileName, byte[] data) {
        try {
            File dir = new File(dirPath);
            if(!dir.isDirectory()) {
                dir.mkdirs();
            }
            File file = new File(dirPath + fileName);
            if (!file.exists()) {
                System.out.println(dirPath + fileName + " is not exist, creating...");
                file.createNewFile();
            }
            else {

//                String os = System.getProperty("os.name");        // 主要针对windows文件不区分大小写问题
//                if(os.toLowerCase().startsWith("win")){
//                    // it's win
//                }
                try {
                    int maxLoop = 9999;
                    int renameSuffixId = 2;
                    String[] cc = fileName.split("\\.");
                    do {
                        Long fileLen = file.length();
                        byte[] fileContent = new byte[fileLen.intValue()];
                        FileInputStream in = new FileInputStream(file);
                        in.read(fileContent);
                        in.close();
                        if(!Arrays.equals(fileContent, data)) {
                            fileName = cc[0] + "_" + renameSuffixId + "." + cc[1];
                            file = new File(dirPath + fileName);
                            if (!file.exists()) {
                                System.out.println("new create file: " + dirPath + fileName);
                                file.createNewFile();
                                break;
                            }
                        }
                        else {
                            break;
                        }
                        renameSuffixId++;
                        maxLoop--;
                    } while (maxLoop > 0);
                }
                catch (Exception e) {
                    System.err.println("exception in read class file..., path: " + dirPath + fileName);
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        }
        catch (Exception e) {
            System.err.println("exception occur while export class.");
            e.printStackTrace();
        }
    }
}
复制代码




写好工具类后，将其打包为jar包文件，（如何打包此类请查看上一篇文章： idea中如何将单个java类导出为jar包文件？），假如打包后的文件命名名 clazzdumpcustagent.jar 。

MENIFEST.MF 文件内容如下：

Manifest-Version: 1.0
Premain-Class: com.youge.api.ClazzDumpCustomAgent
使用该jar包工具，进行运行时class文件查看。

在运行项目时，添加javaagent，进行代码导出：

# 在vm参数中，加入该agent
java -javaagent:D:\server-tool\clazzdumpcustagent.jar=-d=D:/server-tool/tmp/bytecode/exported/;-f2=com/alibaba/dubbo;-r xxx
其中：

　　-d: 设置导出文件的输出目录；

　　-f: 设置需要导出的字节码的前缀；

　　-r: 有该参数代表需要进行包目录的创建，否则生成所有文件到一个目录；

然后可以到指定目录下去查看生成的字节码文件了。

最后，使用java反编译工具，查看 java代码就ok了。（可以直接拖进IDE进行解析）

如果不想自己打包，我打了个包放在网上，有需要可自行下载！  https://download.csdn.net/download/nihe123yiyang/10670937



相信在必要的时候，可以派上用场！