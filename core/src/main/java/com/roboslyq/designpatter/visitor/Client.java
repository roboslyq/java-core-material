package com.roboslyq.designpatter.visitor;

/**
 * 测试类
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.init(10);
        for(IElement element : objectStructure.getArrayList()){
            element.accept(new Visitor1());
        }
    }
}
