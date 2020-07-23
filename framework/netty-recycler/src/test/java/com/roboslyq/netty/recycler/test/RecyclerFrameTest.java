/**
 * Copyright (C), 2015-2020
 * FileName: RecyclerFrameTest
 * Author:   luo.yongqian
 * Date:     2020/7/23 12:43
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/23 12:43      1.0.0               创建
 */
package com.roboslyq.netty.recycler.test;

import com.roboslyq.netty.recycler.Handle;
import com.roboslyq.netty.recycler.Recycler;

/**
 *
 * 〈抽取netty的对象池框架〉
 * @author roboslyq
 * @date 2020/7/23
 * @since 1.0.0
 */
public class RecyclerFrameTest {
    public static void main(String[] args) {
        Recycler<User> recycler = new Recycler<User>() {
            @Override
            protected User newObject(Handle<User> handle) {
                return new User(handle);
            }
        };
        User user = recycler.get();
        user.setId(1);
        user.setName("robslyq");
        user.recycler(user);
        System.out.println(user);
        User user2 = recycler.get();
        System.out.println(user2);

    }

    private static class User {
        private int id;
        private String name;
        private Handle<User> handle;
        public User(Handle<User> handle){
            this.handle = handle;
        }
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
        public void recycler(User user){
            handle.recycle(user);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", handle=" + handle +
                    '}';
        }
    }
}