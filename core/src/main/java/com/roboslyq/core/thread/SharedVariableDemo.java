/**
 * Copyright (C), 2015-2019
 * FileName: SharedVariableDemo
 * Author:   luo.yongqian
 * Date:     2019/5/8 11:27
 * Description: 线程间共享变量测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/8 11:27      1.0.0               创建
 */
package com.roboslyq.core.thread;

/**
 * 〈线程间共享变量测试〉
 *  实现线程间传值，比如Future就是这种原理。
 * @author luo.yongqian
 * @create 2019/5/8
 * @since 1.0.0
 */
public class SharedVariableDemo {
    private User user;

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("roboslyq" + 1);
        System.out.println("user.hashCode()----main--->"+user.hashCode());
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("user.hashCode()----thread1--->"+user.hashCode());
                user.setId(2);
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //等待thread1先执行完，否则可能出现thread2先运行从而取不到thread1更新的最新值。
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("user.hashCode()----thread2--->"+user.hashCode());
                System.out.println(user.getId());
            }
        });
        thread1.start();
        thread2.start();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

class User {
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