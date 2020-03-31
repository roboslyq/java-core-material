package com.roboslyq.jdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe测试
 */
public class UnsafeDemoMain {

    public static int[] arr1;

    public static void main(String[] args) throws IllegalAccessException,InstantiationException {
        try {
            //获取Unsafe对象
            Unsafe unsafe = UnsafeUtils.getUnsafe();
//            test1(unsafe);
            test2(unsafe);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void test2(Unsafe unsafe) {
        // 操作数组
        //获取数组头位置
        final int base = unsafe.arrayBaseOffset(int[].class);
        final int scale = unsafe.arrayIndexScale(int[].class);
        int valueOffset = base + (scale * 0);
//        unsafe.compareAndSwapLong(arr1 , valueOffset, 0, 1);
//
        System.out.println("base " + base);
        System.out.println("scale " + scale);
    }

    private static void test1(Unsafe unsafe) throws InstantiationException, NoSuchFieldException {
        //实例化UnsafeUser
        UserUnsafe userUnsafe = (UserUnsafe) unsafe.allocateInstance(UserUnsafe.class);
        userUnsafe.setId(1);
        userUnsafe.setName("hello ,world 1");
        System.out.println("1------>"+userUnsafe.getId());

        //通过内存偏移地址对userUnsafe对象id进行赋值操作
        Field fieldId = UserUnsafe.class.getDeclaredField("id");
        long fieldOffset = unsafe.objectFieldOffset(fieldId);
        unsafe.putInt(userUnsafe,fieldOffset,10);
        System.out.println("2------>"+userUnsafe.getId());

        //通过内存偏移地址对userUnsafe对象name进行赋值操作
        Field fieldName= UserUnsafe.class.getDeclaredField("name");
        long nameFieldOffset = unsafe.objectFieldOffset(fieldName);
        unsafe.putObject(userUnsafe,nameFieldOffset,"hello ,world 2");
        System.out.println("3------>"+userUnsafe.getName());

        // CAS操作
        //操作成功，因为期望值是10等于内存中的实际值10，最终修改为15
        unsafe.compareAndSwapInt(userUnsafe,fieldOffset,10,15);
        System.out.println("4------>"+userUnsafe.getId());
        //修改失败保留原来值15，因为期望值是10，而内存中实际值为15。
        unsafe.compareAndSwapInt(userUnsafe,fieldOffset,10,10);
        System.out.println("5------>"+userUnsafe.getId());
    }
}

class UserUnsafe{

    public UserUnsafe(){
        System.out.println("constructor was called!!!");
    }

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}