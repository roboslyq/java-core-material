/**
 * Copyright (C), 2015-2021
 * FileName: MessageSourceDemo
 * Author:   roboslyq
 * Date:     2021/10/21 23:36
 * Description: Java标准国际化测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/10/21 23:36      1.0.0               创建
 */
package com.roboslyq.core.messagesource;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 *
 * 〈Java标准国际化测试〉
 * @author roboslyq
 * @date 2021/10/21
 * @since 1.0.0
 */
public class MessageSourceDemo {

    public static void main(String[] args) {
        ResourceBundle resourceBundle = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                return null;
            }

            @Override
            public Enumeration<String> getKeys() {
                return null;
            }
        };
    }

}
