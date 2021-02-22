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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/1/19
 * @since 1.0.0
 */
class Ynet<T> {

}



public class YnetDTO extends Ynet<YnetDTO> {
    public static void main(String[] args) {
        YnetDTO st=new YnetDTO();
        Class clazz=st.getClass();
        System.out.println(clazz.getSuperclass());
        Type type=clazz.getGenericSuperclass();
        System.out.println(type);
        ParameterizedType p=(ParameterizedType)type;
        Class c=(Class) p.getActualTypeArguments()[0];
        System.out.println(c);
    }
}