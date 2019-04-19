package com.roboslyq.core.util;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class WriteClassToFile {
    private static final String FILE_PATH = "D:\\";

    public static void writeToFile(Object o) throws Exception {
        Class clazz = o.getClass();
        String fileName = clazz.getName();
        File file = new File(FILE_PATH + fileName);
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(toByteArray(o));
        FileUtils.writeByteArrayToFile(file,toByteArray(o));
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(o);

    }

    /**
     * 对象转数组
     *
     * @return
     * @paramobj
     */
    public static byte[] toByteArray(Object obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @return
     * @parambytes
     */
    public Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

}
