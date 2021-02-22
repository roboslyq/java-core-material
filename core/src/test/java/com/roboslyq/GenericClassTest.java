/**
 * Copyright (C), 2015-2021
 * FileName: GenericClassTest
 * Author:   luo.yongqian
 * Date:     2021/1/19 21:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/1/19 21:28      1.0.0               创建
 */
package com.roboslyq;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/1/19
 * @since 1.0.0
 */
public class GenericClassTest {

    public static void main(String[] args) {
        Type genericSuperType = C.class.getGenericSuperclass();


        Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

        Type actualTypeParam = actualTypeParams[0];
        if (actualTypeParam instanceof ParameterizedType) {
            actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
        }

    }
}


class B {

}
class D extends B{

}

class C<T extends B>{

    public void findList(List<T> test){
        System.out.printf("hello");
    }
}
