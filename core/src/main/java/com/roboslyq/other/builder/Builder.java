/**
 * Copyright (C), 2015-2020
 * FileName: Builder
 * Author:   luo.yongqian
 * Date:     2020/3/4 9:05
 * Description: 通过的Builder
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/4 9:05      1.0.0               创建
 */
package com.roboslyq.other.builder;

/**
 *
 * 〈通过的Builder〉
 * @author luo.yongqian
 * @date 2020/3/4
 * @since 1.0.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class Builder<T> {
    /**
     * 提供者：实例构造器Object::new就是其中一个，可以用来生产即体的实例
     */
    private final Supplier<T> instantiator;
    /**
     * 消费者：即setter方法集合，先保存，做惰性求值(在最末build()时触发)
     */
    private List<Consumer<T>> modifiers = new ArrayList<>();

    /**
     * 构造函数
     * @param instantiator
     */
    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    /**
     * 静态方法调用构造函数，从而不需要new即可以使用Builder构造器。
     * @param instantiator
     * @param <T>
     * @return
     */
    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    /**
     * 设置一个值
     * @param consumer 与setter方法对应
     * @param p1 需要设置的值,即setter方法中对应的值
     * @param <P1>
     * @return
     */
    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        // consumer的入参是
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }
    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }
    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }
    public T build() {
        //Object:new ,返回对象实例
        T value = instantiator.get();
        //modifier是一个Consumer，与setter方法对应。
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }
    /**
     * 2 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }
    /**
     * 3 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }
}