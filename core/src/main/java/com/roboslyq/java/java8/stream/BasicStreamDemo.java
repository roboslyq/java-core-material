/**
 * Copyright (C), 2015-2019
 * FileName: BasicStreamDemo
 * Author:   luo.yongqian
 * Date:     2019/4/28 11:00
 * Description: 第一个Sream示例
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/28 11:00      1.0.0               创建
 */
package com.roboslyq.java.java8.stream;

import com.roboslyq.java.ModelDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 〈第一个Sream示例〉
 * Stream与旧板for遍历区别：
 *  1、for是客户端(调用者)主动去获取数据，在数据仓库/集合外部实现（外部迭代）。Stream是仓库内部实现，然后将数据推送给客户端。
 *  2、for循环有大量样板代码
 *  3、for实现原理 ：首先调用iterator 方法，产生一个新的Iterator 对象，进而控制整个迭代过程，这就是外部迭代
 *            Application                    Collection
 *             hasNext() --询问集合-->
 *              hasNext  <---返回----
 *               next()  -------->
 *              value    <---返回元素----
 *  4、Stream内部迭代
 *            Application                   Collection
 *               发起操作     ------------->
 *                                          内部迭代操作(Spliterator:)
 *               返回操作结果  <-------------
 *  5、惰性求值方法：自己本身不会执行，需要结束操作后再触发
 *  6、及早求值方法-终止操作，会执行相关所有操作
 * @author luo.yongqian
 * @create 2019/4/28
 * @since 1.0.0
 */
public class BasicStreamDemo {
    public static void main(String[] args) {
      List<ModelDemo> modelDemos = init();
      //演示不会打印，因为没有终止操作符
       modelDemos.stream().peek(md ->{
            printThread();
            System.out.println("没有打印1"+ md.getName());
       })
        .filter(md -> md.getId() > 5)
        .peek(md ->{
            printThread();
            System.out.println("没有打印2"+ md.getName());
        });
      //
      List<ModelDemo> reslut = modelDemos.stream().map(md ->{
          printThread();
          System.out.println("没有打印3"+ md.getName());
          return md;
      })
      .filter(md -> md.getId() > 5)
      .map(md ->{
          printThread();
          System.out.println("第二次Map "+ md.getName());
          return md;
      })
      .sorted()
      .collect(Collectors.toList());
      ;
      for(ModelDemo modelDemo : reslut){
          printThread();
          System.out.println("第三次 foreach打印"+modelDemo.getName());
      }
    }

    /**
     * 初始化
     * @return
     */
    public static List<ModelDemo>  init(){
        List<ModelDemo> listModelDemo = new ArrayList<>();
        for(int i=0;i<10;i++){
            ModelDemo modelDemo = new ModelDemo();
            modelDemo.setId(i);
            modelDemo.setName("roboslyq_" + i);
            listModelDemo.add(modelDemo);
        }
        return listModelDemo;
    }


    static void  printThread(){
        System.out.println("当前线程ID-"+Thread.currentThread().getId());
    }
}