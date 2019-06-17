/**
 * Copyright (C), 2015-2019
 * FileName: ServiceLoaderDemo
 * Author:   luo.yongqian
 * Date:     2019/6/17 9:51
 * Description: SPI扩展DEMO
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/17 9:51      1.0.0               创建
 */
package com.roboslyq.util.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * 〈SPI扩展DEMO〉
 * @author luo.yongqian
 * @create 2019/6/17
 * @since 1.0.0
 */
public class ServiceLoaderDemo {
    public static void main(String[] args) {
        ServiceLoader serviceLoader = ServiceLoader.load(UserInterface.class);
        Iterator<UserInterface> matcherIter = serviceLoader.iterator();
        while (matcherIter.hasNext()) {
            UserInterface userService = matcherIter.next();
            System.out.println(userService.getClass().getName());
            System.out.println(userService.getUserName());
        }
    }
}