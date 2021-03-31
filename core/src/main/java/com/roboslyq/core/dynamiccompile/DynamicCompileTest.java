/**
 * Copyright (C), 2015-2020
 * FileName: DynamicCompileTest
 * Author:   luo.yongqian
 * Date:     2020/11/24 16:47
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/11/24 16:47      1.0.0               创建
 */
package com.roboslyq.core.dynamiccompile;

import org.apache.commons.lang3.reflect.FieldUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/11/24
 * @since 1.0.0
 */
public class DynamicCompileTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 编译指定的类
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result=compiler.run(null, null, null,"D:\\IdeaProjects_community\\java-core-material\\core\\src\\main\\java\\com\\roboslyq\\core\\dynamiccompile\\HelloWorld.java");
        System.out.println(result==0?"编译成功":"编译失败");

        // 编译指定的字符串，字符串来源可以是txt或者db或者其它任务渠道。但是要生成一个中间临时java源文件，然后编译
        String str="public class HelloWorld{\n" +
                "public static String Key=\"hello wolrd\" ; \n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"helloworld\");\n" +
                "\t}\n" +
                "}";
        File file = new File("D:\\tmp\\HelloWorld.java");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(str.getBytes());
        fos.close();
        result=compiler.run(null, null, null,"D:\\tmp\\HelloWorld.java");
        System.out.println(result==0?"编译成功":"编译失败");

        URLClassLoader ucl = new URLClassLoader(new URL[]{new URL("file://D:/tmp/")});
        Class<?> cls = ucl.loadClass("HelloWorld");
        Object object = cls.newInstance();
        for(Field field :  FieldUtils.getAllFields(cls)){
            System.out.println(field.getName());
            System.out.println(field.get(object));
        }
        Method method = cls.getDeclaredMethod("main",String[].class);
        String[] s = {};
        method.invoke(null,(Object)s);

    }

}