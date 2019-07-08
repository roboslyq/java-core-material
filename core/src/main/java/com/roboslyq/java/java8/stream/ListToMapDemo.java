/**
 * Copyright (C), 2015-2019
 * FileName: ListToMapDemo
 * Author:   luo.yongqian
 * Date:     2019/5/20 16:54
 * Description: Java Stream list转map demo
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/20 16:54      1.0.0               创建
 */
package com.roboslyq.java.java8.stream;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * 〈Java Stream list转map demo〉
 * @author luo.yongqian
 * @create 2019/5/20
 * @since 1.0.0
 */

public class ListToMapDemo {
    public static void main(String[] args) {
        User[] userArray = User.userHelper(5);
        // key 目前为Object，可以指定具体类型，根据不同需要来定
        Map<Object,String> res =  Arrays.stream(userArray)
//                .collect(Collectors.toMap(User::getId, User::getName));
         .collect(Collectors.toMap(user -> user.getId() + "-" + user.getName(), User::getName));
        res.forEach((key,value) ->{
           System.out.println("map key is : " + key + " value is :" + value);
       });
    }
}

@Getter
@Setter
class User{
    Integer id;
    String name;

    static User[] userHelper(int userSize){
        User[] userArray = new User[userSize];
        for(int j = 0 ;j<userSize ; j++){
            User user = new User();
            user.setId(j);
            user.setName("roboslyq_" + j);
            userArray[j] = user ;
        }
        return  userArray;
    }
}