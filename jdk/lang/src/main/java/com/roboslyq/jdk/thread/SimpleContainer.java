package com.roboslyq.jdk.thread;

import java.util.ArrayList;
import java.util.List;

public class SimpleContainer {

    private volatile List<Integer> container = new ArrayList<Integer>();

    public void add(int value){
        container.add(value);
    }

    public int size(){
        return this.container.size();
    }
}
