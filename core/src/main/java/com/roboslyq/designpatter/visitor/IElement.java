package com.roboslyq.designpatter.visitor;

/**
 * 具体元素
 * 包含两件事：接收访问者和做具体的事件
 */
public interface IElement {
    //元素接受访问者
    void accept(IVisitor visitor);
    //元素要做的事情
    void doSomething();
}
