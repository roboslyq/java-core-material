package com.roboslyq.designpatter.visitor;

public class ConcreteElement1 implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        //元素将自己传递给访问者
        visitor.visit(this);
    }

    @Override
    /**
     * 元素的功能
     */
    public void doSomething() {
        System.out.println("ConcreteElement1 do things ...");
    }
}
