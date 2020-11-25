/**
 * Copyright (C), 2015-2020
 * FileName: AsmUtils
 * Author:   luo.yongqian
 * Date:     2020/10/28 16:59
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/10/28 16:59      1.0.0               创建
 */
package com.roboslyq.cglib.asm;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/10/28
 * @since 1.0.0
 */
public class AsmUtils {
    private static Method defineClass;
    private static final ProtectionDomain PROTECTION_DOMAIN;

    static {
        PROTECTION_DOMAIN = AccessController.doPrivileged(new PrivilegedAction<ProtectionDomain>() {
            public ProtectionDomain run() {
                return HelloWorldDump.class.getProtectionDomain();
            }
        });

        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Class loader = Class.forName("java.lang.ClassLoader"); // JVM crash w/o this
                    defineClass = loader.getDeclaredMethod("defineClass",
                            new Class[] { String.class, byte[].class,
                                    Integer.TYPE, Integer.TYPE,
                                    ProtectionDomain.class });
                    defineClass.setAccessible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     *
     * <p>根据字节码获得Class对象</p>
     *
     * @param className 类全名
     * @param b 字节码
     * @param loader
     * @return
     */
    public static Class<?> defineClass(String className, byte[] b,ClassLoader loader) {
        Object[] args = new Object[] { className, b, 0,
                b.length, PROTECTION_DOMAIN };
        try {
            return (Class<?>) defineClass.invoke(loader, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getMethodTest(){
        return
                "System.out.println(\" 打印测试1 \");"+
                        "System.out.println(\" 打印测试2 \");"+
                        "System.out.println(\" 打印测试3 \");";
    }
}
