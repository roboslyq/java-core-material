package com.roboslyq.core.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.*;

import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

public class ASMDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassWriter cw = new ClassWriter(0);
        //通过visit方法确定类的头部信息
        cw.visit(
                //参数一：版本信息
                Opcodes.V1_5
                //参数二：类修改符，通过组合相关实现，与linux权限原理一致("Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE")
                ,Opcodes.ACC_PUBLIC
                //参数三：类型全路径
                ,"com/roboslyq/core/bytecode/AsmTest"
                //类签名，如果类不是通用类，并且不扩展或实现泛型类或接口，则可能为null。
                ,null
                //超类名称，如果是接口或超类为Object则可能为null。Java仅单继承，故只有一个参数
                , "com/roboslyq/core/bytecode/BytecodeParent"
                //实现接口：可以实现多个接口，故参数为数组
                ,new String[] {"com/roboslyq/core/bytecode/BytecodeInterface1"
                                ,"com/roboslyq/core/bytecode/BytecodeInterface2"
                            }
                );

        // 定义类的属性
        cw.visitField(
                    //参数一：属性修饰符，原理同头部信息
                    Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                    //参数名称
                    "LESS",
                    //参数类型:详情见org.objectweb.asm.Type.getTypeInternal()
                    "J",
                    //字段签名，若字段类型不是泛型则可以为null
                    null,
                    //字段初始值
                    -1
                    ).visitEnd();
        cw.visitField(
                    Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC
                    ,"EQUAL"
                    , "I"
                    , null
                    , 0
                    ).visitEnd();
        cw.visitField(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC
                ,"GREATER"
                , "I"
                , null
                , 1
        ).visitEnd();

        // 定义类的方法
        MethodVisitor mv= cw.visitMethod(
                    //方法签名
                    Opcodes.ACC_PUBLIC
                    //方法名称
                    , "compareTo"
                    //方法参数：详情可以参考JVM字节码相关
                    ,"(Ljava/lang/Object;)I"
                    , null
                    , null
                    );
        //添加方法体
        mv.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        mv.visitLdcInsn("方式一:方法开始运行");
        mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        //方法定义结束
        mv.visitEnd();
        //使cw类已经完成
        cw.visitEnd();
        //将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File("core/target/classes/com/roboslyq/core/bytecode/AsmTest.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();

        ClassLoader classLoader =  new MyClassLoader();
        Class clazz = ((MyClassLoader) classLoader).findClass("AsmTest");
        System.out.println(clazz);
    }
}

