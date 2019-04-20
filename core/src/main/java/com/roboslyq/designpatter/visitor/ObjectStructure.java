package com.roboslyq.designpatter.visitor;

import java.util.ArrayList;

/**
 * 保存元素的容器，简单的进行初始化。
 * 并且提供获取容器的接口（遍列或者直接返回）
 */
public class ObjectStructure {
    private final ArrayList<IElement> arrayList = new ArrayList();
    /**
     * 初始化元素个数
     * @param elementSize
     */
    public void init(int elementSize){
        for(int i = 0 ; i < elementSize ; i ++){
            int a= i % 2;
            if(a == 0 ){
                arrayList.add (new ConcreteElement1());
            }else{
                arrayList.add (new ConcreteElement2());
            }
        }
    }

    public ArrayList<IElement> getArrayList() {
        return arrayList;
    }
}
