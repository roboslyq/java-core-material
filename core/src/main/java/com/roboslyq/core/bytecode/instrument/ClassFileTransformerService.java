/**
 * Copyright (C), 2015-2019
 * FileName: ClassFileTransformerService
 * Author:   luo.yongqian
 * Date:     2019/4/19 14:58
 * Description: write java object to class file
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/19 14:58      1.0.0               创建
 */
package com.roboslyq.core.bytecode.instrument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * JDK5 提供的Instrument 功能实现字节码操作
 * 1、功能说明 ：
 *      启动时往 Java 虚拟机中挂上一个用户定义的 hook 程序，可以在装入特定类的时候改变特定类的字节码，从而改变该类的行为。但是其缺点也是明显的：
        instrument 包是在整个虚拟机上挂了一个钩子程序，每次装入一个新类的时候，都必须执行一遍这段程序，即使这个类不需要改变。
 *      直接改变字节码事实上类似于直接改写 class 文件，无论是调用 ClassFileTransformer. transform(ClassLoader loader, String className,
 *      Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)，
 *      还是 instrument.redefineClasses(ClassDefinition[] definitions)，都必须提供新 Java 类的字节码。也就是说，
 *      同直接改写 class 文件一样，使用 instrument 也必须了解想改造的方法相对类首部的偏移量，才能在适当的位置上插入新的代码。
 *      尽管 instrument 可以改造类，但事实上，instrument 更适用于监控和控制虚拟机的行为。
 * 2、接口ClassFileTransformer
 *      通过实现接口{@link ClassFileTransformer}，将运行时的Object对象写入到对应的Class文件中，以方便查看Object对应的字节码。
 * 3、基本原理 ：
 *      获取Instrumentation实例的方法有2种：
 *      --虚拟机启动时，通过agent class的premain方法获得
 *      --虚拟机启动后，通过agent class的agentmain方法获得
 *      一旦agent参数获取到一个instrumentation，agent将会在任意时候调用实例中的方法。
 *       agent应该以jar包的形式存在，也就是说agent所在的类需要单独打包一个jar包，jar包的manifest文件指定agent class。
 *       文件中包含Premain-Class属性，agent class类必须实现public static premain 方法，实际应用的main方法在这个方法之后执行。
 * 注：(1)使用ObjectOutputStream方法序列化的格式与Class格式不一样，不能反编译只能反序列化。
 *     (2)此代码主要参考https://www.cnblogs.com/yougewe/p/9651555.html 相应文章
 * VM参数样例：
 *      -javaagent:D:\server-tool\clazzdumpcustagent.jar=-d=D:/server-tool/;-f=com/sun/proxy;-r
 *      -javaagent:D:\server-tool\clazzdumpcustagent.jar --->表示java Agent客户端所在Jar包。其中jar包中存在“META-INF/MANIFEST.MF”配置文件
 *          指定了需要运行的类
 *      -d=D:/server-tool/;  指定了文件保存的路径
 *      -f=com/sun/proxy; 指定了过滤器，只有符合这个条件的前缀的类名，才会生成class
 *      -r  仅需要这个key，表示需要创建目录
 * @author luo.yongqian
 * @create 2019/4/19
 * @since 1.0.0
 */
public class ClassFileTransformerService implements ClassFileTransformer {
    /**
     * 导出过滤表达式，此处为类名前缀， 以 -f 参数指定
     *
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

    public ClassFileTransformerService(String exportBaseDir, String filterStr) {
        this(exportBaseDir, filterStr, false);
    }

    public ClassFileTransformerService(String exportBaseDir, String filterStr, boolean packageRecursive) {
        if (exportBaseDir != null) {
            this.exportBaseDir = exportBaseDir;
        }
        this.packageRecursive = packageRecursive;
        this.filterStr = filterStr;
    }

    /**
     * 入口地址（打成Jar包，配置好VM参数后，JVM会自动调用此方法）
     * @param agentArgs agent参数
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs: " + agentArgs);
        String exportDir = null;
        String filterStr = null;
        boolean recursiveDir = false;
        if (agentArgs != null) {
            if (agentArgs.contains(";")) {
                String[] args = agentArgs.split(";");
                for (String param1 : args) {
                    String[] kv = param1.split("=");
                    if ("-d".equalsIgnoreCase(kv[0])) {
                        exportDir = kv[1];
                    } else if ("-f".equalsIgnoreCase(kv[0])) {
                        filterStr = kv[1];
                    } else if ("-r".equalsIgnoreCase(kv[0])) {
                        recursiveDir = true;
                    }
                }
            } else {
                filterStr = agentArgs;
            }
        }
        inst.addTransformer(new ClassFileTransformerService(exportDir, filterStr, recursiveDir));
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (needExportClass(className)) {
            int lastSeparatorIndex = className.lastIndexOf("/") + 1;
            String fileName = className.substring(lastSeparatorIndex) + ".class";
            String exportDir = exportBaseDir;
            if (packageRecursive) {
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
        if (filterStr != null) {
            if (className.startsWith(filterStr)) {
                return true;
            } else {
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
     * @param dirPath  导出目录
     * @param fileName 导出文件名
     * @param data     字节流
     */
    private void exportClazzToFile(String dirPath, String fileName, byte[] data) {
        try {
            File dir = new File(dirPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            File file = new File(dirPath + fileName);
            if (!file.exists()) {
                System.out.println(dirPath + fileName + " is not exist, creating...");
                file.createNewFile();
            } else {

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
                        if (!Arrays.equals(fileContent, data)) {
                            fileName = cc[0] + "_" + renameSuffixId + "." + cc[1];
                            file = new File(dirPath + fileName);
                            if (!file.exists()) {
                                System.out.println("new create file: " + dirPath + fileName);
                                file.createNewFile();
                                break;
                            }
                        } else {
                            break;
                        }
                        renameSuffixId++;
                        maxLoop--;
                    } while (maxLoop > 0);
                } catch (Exception e) {
                    System.err.println("exception in read class file..., path: " + dirPath + fileName);
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            System.err.println("exception occur while export class.");
            e.printStackTrace();
        }
    }
}
