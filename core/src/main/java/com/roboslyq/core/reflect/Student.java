/**
 * Copyright (C), 2015-2019
 * FileName: Student
 * Author:   luo.yongqian
 * Date:     2019/4/19 15:44
 * Description: 测试对象序列化与反序列化
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/19 15:44      1.0.0               创建
 */
package com.roboslyq.core.reflect;

/**
 *
 * 〈测试对象序列化与反序列化〉
 * @author luo.yongqian
 * @create 2019/4/19
 * @since 1.0.0
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Student implements Serializable {

    private static final long serialVersionUID = -6601903208557464574L;

    private String id;
    private String name;

    public Student() {
        super();
    }

    public Student(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        Student o = new Student("1001", "张三");
        FileOutputStream os = new FileOutputStream("D:\\student.data");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(o);
        oos.flush();
        oos.close();

        FileInputStream is = new FileInputStream("D:\\student.data");
        ObjectInputStream ois = new ObjectInputStream(is);
        Student deserialized = (Student) ois.readObject();
        ois.close();
        System.out.println("学号：" + deserialized.getId());
        System.out.println("姓名：" + deserialized.getName());
    }
}

