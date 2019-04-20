package com.roboslyq.designpatter.visitor;

public class ConcreteElement2 implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteElement2 do things ...");
    }
}
