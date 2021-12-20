package com.roboslyq.core.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LyqClassloader extends ClassLoader {
    private final static String CLASS_PATH_PREFIX = "core/target/classes/";
    @Override
    protected Class<?> findClass(String name)  {
        byte[] bytes = null;
        try {
            bytes = doFindClassFromLocalFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //此方法已经过时了
//        return defineClass(bytes,0,bytes.length);
        //此方法为替代方法，强调已经知道类的名称"com.roboslyq.core.classloader.ClassloaderHello"
        return defineClass(name,bytes,0,bytes.length);
    }
    public byte[] doFindClassFromLocalFile(String name) throws IOException {
        File file = new File(CLASS_PATH_PREFIX + getFilePath(name));
        System.out.println(file.getAbsolutePath());
        FileInputStream fileInputStream = new FileInputStream(file);
//        return fileInputStream.readAllBytes();
        return null;
    }

    public String getFilePath(String classPath){
        return classPath.replaceAll("\\.","/") + ".class";
    }
}
