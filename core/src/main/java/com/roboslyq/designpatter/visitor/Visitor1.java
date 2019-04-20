package com.roboslyq.designpatter.visitor;

/**
 * 具体的访客实现
 */
public class Visitor1 implements IVisitor {
    @Override
    public void visit(IElement element) {
        element.doSomething();
    }
}
