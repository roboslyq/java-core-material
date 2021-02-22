/**
 * Copyright (C), 2015-2021
 * FileName: Student
 * Author:   luo.yongqian
 * Date:     2021/1/19 21:35
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/1/19 21:35      1.0.0               创建
 */
package com.roboslyq;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/1/19
 * @since 1.0.0
 */
class Ynet1<T> {
    List<String> list1;
    List list2;
    Map<String,Long> map1;
    Map map2;
    Map.Entry<Long,Short> map3;
}


public class YnetDTO1 extends Ynet1<YnetDTO1> {
    public static void main(String[] args) {
        Field[] fields = Ynet1.class.getDeclaredFields();
        for(Field f:fields){
            //是否是ParameterizedType
            System.out.println(f.getName()+":"+(f.getGenericType() instanceof ParameterizedType));
            if(f.getGenericType() instanceof ParameterizedType){
                ParameterizedType p=(ParameterizedType)f.getGenericType();
                Class c=(Class) p.getActualTypeArguments()[0];
                System.out.println("class类型：" + c);
            }

        }
    }
}