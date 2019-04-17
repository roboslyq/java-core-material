package com.roboslyq.core.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

public class TestAsm implements Opcodes {
            public static void main(String[] args) throws Exception{
                ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_MAXS);//这样设置才能自动计算
                String className="Example";
                cw.visit(V9,ACC_PUBLIC,className,null,"java/lang/Object",new String[]{"java/lang/Cloneable",ITest.class.getName().replace('.','/')});
         
                String field="test";
                Object defaultValue=123d;
                String setMd="setTest";
                String getMd="getTest";
                cw.visitField(ACC_PRIVATE,field,"D",null,null).visitEnd();
         
                MethodVisitor mv=cw.visitMethod(ACC_PUBLIC,getMd,"()D",null,null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD,0);
                mv.visitFieldInsn(GETFIELD,className,field,"D");
                mv.visitInsn(DRETURN);
                mv.visitMaxs(0,0);// 自动计算局部变量和栈大小
                mv.visitEnd();
                cw.visitEnd();
         
                mv=cw.visitMethod(ACC_PUBLIC,setMd,"(D)V",null,null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD,0);
                mv.visitVarInsn(DLOAD,1);
                mv.visitFieldInsn(PUTFIELD,className,field,"D");
                mv.visitInsn(RETURN);
                mv.visitMaxs(0,0);// 自动计算
                mv.visitEnd();
         
                // 下面产生构造方法
                mv=cw.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD,0);
                mv.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V");
                if(defaultValue != null){
                    //在构造方法中赋默认值
                    mv.visitVarInsn(ALOAD,0);
                    mv.visitLdcInsn(Double.parseDouble(defaultValue.toString()));
                    mv.visitFieldInsn(PUTFIELD,className,field,"D");
                }
                mv.visitInsn(RETURN);
                mv.visitMaxs(0,0);// 自动计算
                mv.visitEnd();
         
                cw.visitEnd();
         
                byte[] bs=cw.toByteArray();
                // FileUtil.writeFile("Example.class",bs);
                File file = new File("core/target/classes/com/roboslyq/core/bytecode/Example.class");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bs);
                fos.close();
                MyCLassLoader loader=new MyCLassLoader();
                Class c=loader.defineClass(bs);
         
                c.getConstructor(new Class[0]);
                ITest ins=(ITest)c.newInstance();
                //ins.setTest(100.0d);
                System.out.println(ins.getTest());
            }
         
            public interface ITest{
                public double getTest();
         
                public void setTest(double d);
            }
         
            public static class MyCLassLoader extends ClassLoader{
                public Class defineClass(byte[] data){
                    return super.defineClass(null,data,0,data.length,null);
                }
            }
        }