package com.roboslyq.core.bytecode.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * 基于Asm相关原理，这个方法不太能通用。需要根据自己的具体需求来实现相应的字节码生成。
 */
public class AsmService {
    public void doAsm(String className) throws IOException {
        {

            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            /**
             *  Step 1:
             *  start by getting ourselves a ClassWriter instance and "visiting" the class itself:
             *  通过visit方法确定类的头部信息
             */
            cw.visit(
                    //参数一：版本信息
                    Opcodes.V1_8
                    //参数二：类修改符，通过组合相关实现，与linux权限原理一致("Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE")
                    , Opcodes.ACC_PUBLIC
                    //参数三：类型全路径（ package and name）
                    , className
                    //类签名，如果类不是通用类，并且不扩展或实现泛型类或接口，则可能为null。
                    , null
                    //超类名称，如果是接口或超类为Object则可能为null。Java仅单继承，故只有一个参数
                    , "java/lang/Object"
                    //实现接口：可以实现多个接口，故参数为数组
                    , new String[]{"com/roboslyq/core/bytecode/asm/ICalculator"}
            );
            // 定义类的方法
            //构造函数
            /**
             * step 2:
             * Now we have a way to walk through the class, just as if we were writing source code. However,
             * we have to be more explicit because there is no compiler to do things for us. This means we have
             * to explicitly define a constructor, even a no-arg constructor, to make sure our class has one:
             */
            MethodVisitor constructor = cw.visitMethod(
                                                Opcodes.ACC_PUBLIC
                                                , "<init>"
                                                , "()V"
                                                , null
                                                , null
                                            );
            constructor.visitCode();// Start the code for this method
            //  Load "this" onto the stackspecifically "this" since we are invoking a superclass constructor on ourselves.
            //  Because we are inside a method, "this" is available to us as our first local variable (number 0).
            //  So we can load it onto the stack.
            constructor.visitVarInsn(Opcodes.ALOAD, 0);
            constructor.visitMethodInsn(Opcodes.INVOKESPECIAL           // Invoke an instance method (non-virtual)
                                        , "java/lang/Object"    // Class on which the method is defined
                                        , "<init>"              // Name of the method
                                        , "()V"             // Descriptor
                                        ,false              // Is this class an interface?
                                        );
            constructor.visitInsn(Opcodes.RETURN);                     // End the constructor method
            /*
             * we have to explicitly return from the method; that's another thing the compiler does for us.
             * We then call visitMaxs() to provide a couple numbers to ASM so when this method is run,
             * Java can make sure there is enough memory space for it. First, we specify a maximum stack size.
             * Since we only push one thing onto the stack, this is 1. Second, we specify the maximum number of local variables.
             *  Even though we declared no local variables, and we have no parameters, our "this" counts as a local variable,
             * so we have to set it to 1.
             */
            constructor.visitMaxs(1, 1);         // Specify max stack and local vars
            constructor.visitEnd();

            // 定义类的属性
            cw.visitField(
                    //参数一：属性修饰符，原理同头部信息
                    Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                    //参数名称
                    "LESS",
                    //参数类型:详情见org.objectweb.asm.Type.getTypeInternal()
                    "I",
                    //字段签名，若字段类型不是泛型则可以为null
                    null,
                    //字段初始值
                    -1
            ).visitEnd();

            //定义普通方法
            MethodVisitor mv = cw.visitMethod(
                    Opcodes.ACC_PUBLIC,                         // public method
                    "add",                              // name
                    "(II)I",                            // descriptor
                    null,                               // signature (null means not generic)
                    null);                              // exceptions (array of strings)
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ILOAD, 1);                  // Load int value onto stack
            mv.visitVarInsn(Opcodes.ILOAD, 2);                  // Load int value onto stack
            mv.visitInsn(Opcodes.IADD);                         // Integer add from stack and push to stack
            mv.visitInsn(Opcodes.IRETURN);                      // Return integer from top of stack
            mv.visitMaxs(2, 3);                         // Specify max stack and local vars
            cw.visitEnd();                              // Finish the class definition
            //方法定义结束
            mv.visitEnd();
            //使cw类已经完成
            cw.visitEnd();
            byte[] bs=cw.toByteArray();
            File file = new File("core/target/classes/" + className + ".class");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bs);
            fos.close();
        }
    }
}
