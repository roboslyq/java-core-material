/**
 * Copyright (C), 2015-2019
 * FileName: WriteJavaObjectToClassFile
 * Author:   luo.yongqian
 * Date:     2019/4/19 14:58
 * Description: write java object to class file
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/19 14:58      1.0.0               创建
 */
package com.roboslyq.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * 1、功能说明 ：
 *      通过实现接口{@link ClassFileTransformer}，将运行时的Object对象写入到对应的Class文件中，以方便查看Object对应的字节码。
 * 2、基本原理 ：
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
public class WriteJavaObjectToClassFile implements ClassFileTransformer {
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

    public WriteJavaObjectToClassFile(String exportBaseDir, String filterStr) {
        this(exportBaseDir, filterStr, false);
    }

    public WriteJavaObjectToClassFile(String exportBaseDir, String filterStr, boolean packageRecursive) {
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
        inst.addTransformer(new WriteJavaObjectToClassFile(exportDir, filterStr, recursiveDir));
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
