/**
 * Copyright (C), 2015-2019
 * FileName: InitClass
 * Author:   luo.yongqian
 * Date:     2019/8/13 12:34
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/13 12:34      1.0.0               创建
 */
package com.roboslyq.java.basic;

/**
 *
 * 〈
 * Java中常规赋值顺序：
 *  1、父类的静态变量赋值
 *  2、自身的静态变量赋值
 *  3、父类成员变量赋值和父类块赋值
 *  4、父类构造函数赋值
 *  5、自身成员变量赋值和自身块赋值
 *  6、自身构造函数赋值
 * 〉
 * 注意：实例初始化不一定要在类初始化结束之后才开始初始化。类的生命周期是：加载->验证->准备->解析->初始化->使用->卸载，
 * 只有在准备阶段和初始化阶段才会涉及类变量的初始化和赋值
 *
 * @author luo.yongqian
 * @create 2019/8/13
 * @since 1.0.0
 */
public class InitClass
{
    public static void main (  String [] args) {
        staticFunction();
    }
    //第一个静态块，而静态块是初始化实例方法，所以先调用构造函数
    static  InitClass  st = new InitClass();
    static
    {
        System.out.println ( "1" );

    }

    {
    System.out.println( "2");
    }


    InitClass() {
        System.out.println("3");
        System . out.  println ("a="+ a+",b="+b);

    }
    public static void staticFunction(){
        System.out. println("4" );

    }
    int  a  = 110  ;
    static int  b =112  ;
}