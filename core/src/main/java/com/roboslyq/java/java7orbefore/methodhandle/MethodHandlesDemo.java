//package com.roboslyq.java.java7orbefore.methodhandle;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//
///**
// * MethodHandle与VarHandle示例
// * 1、VarHandle主要功能类似于Unsafe这个类，比较偏向于底层。由于Unsafe不安全(权限太大！！！)，所以官文并不
// *   推荐普通用户自己使用。
// */
//public class MethodHandlesDemo {
//    /**
//     * 定义属性
//     */
//    private int id;
//    private String name;
//    /**
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        /* 打印结果如下：
//         * true
//         * 10
//         * true
//         * luoyq
//         * false
//         * 10
//         * true
//         * luoyq1
//         */
//        System.out.println(1 << 30);
//        MethodHandlesDemo demo = new MethodHandlesDemo();
//        //compareAndSet参数类型：{@code (CT1 ct1, ..., CTn ctn, T expectedValue, T newValue)}
//        //下面方法对应的是：demo为具体对象，0是期望值，10是目标值
//        boolean result1_1 = ID_HANDLE.compareAndSet(demo, 0, 10);
//        boolean result1_2 = NAME_HANDLE.compareAndSet(demo, null, "luoyq");
//        //true:交换成功并赋值
//        System.out.println(result1_1);
//        //10
//        System.out.println(demo.id);
//        //true
//        System.out.println(result1_2);
//        //luoyq
//        System.out.println(demo.name);
//
//        boolean result2_1 = ID_HANDLE.compareAndSet(demo, 0, 11);
//
//        boolean result2_2 = NAME_HANDLE.compareAndSet(demo, "luoyq", "luoyq1");
//        //false
//        System.out.println(result2_1);
//        //10
//        System.out.println(demo.id);
//        //true
//        System.out.println(result2_2);
//        //luoyq1
//        System.out.println(demo.name);
//    }
//
//    /**
//     * 创建一个VarHandle对象
//     */
//    private static final VarHandle ID_HANDLE;
//    private static final VarHandle NAME_HANDLE;
//    /*
//     *静态代码块进行初始化
//     */
//    static {
//        try {
//            //MethodHandles.lookup()初始化
//            MethodHandles.Lookup l = MethodHandles.lookup();
//
//            /*
//             * 通过MethodHandles.lookup()进行变量包装
//             * 1、参数1：变量所有类类型
//             * 2、参数2: 变量的所属名称
//             * 3、参数3：变量类型
//             */
//            ID_HANDLE = l.findVarHandle(MethodHandlesDemo.class, "id", int.class);
//            NAME_HANDLE = l.findVarHandle(MethodHandlesDemo.class, "name", String.class);
//        } catch (ReflectiveOperationException e) {
//            throw new Error(e);
//        }
//    }
//
//}
