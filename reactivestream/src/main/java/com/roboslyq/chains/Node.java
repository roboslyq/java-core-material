/**
 * Copyright (C), 2015-2019
 * FileName: Node
 * Author:   luo.yongqian
 * Date:     2019/6/25 10:12
 * Description: 延迟链式调用简单实现
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 10:12      1.0.0               创建
 */
package com.roboslyq.chains;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * 〈延迟链式调用简单实现〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class Node<T> implements Provider{
    //前一节点
    public Node previous;
    //
    public static Supplier supplier;
    //是否头节点
    public boolean isHead = false;

    //每个节点的传入的lambda表达式，不同节点可以根据实际情况来实现
    public Consumer<T> consumer;

    //构造函数
    public Node(Node previous) {
        this.previous = previous;
    }

    /**
     * 构建链
     * @return
     */
    public Node provide(Consumer<T> consumer) {
        this.consumer = consumer;
        return  new Node(this);
    }

    @Override
    public void deal(Subscriber subscriber) {
        if(this.isHead){
            T t = (T)supplier.get();
            subscriber.subscribe(t);
        }else{
            deal(new NodeSubscriber(subscriber));
        }
    }


    class NodeSubscriber implements Subscriber<T>{
         Subscriber downStream;

         public NodeSubscriber(Subscriber downStream) {
             this.downStream = downStream;
         }

         @Override
        public void subscribe(T o) {
            System.out.println(o);
        }
    }

}