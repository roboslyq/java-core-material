package com.roboslyq.core.bytecode.asm;

/**
 * 接口
 * we would like to be able to use it from regular Java code (i.e. not using reflection).
 * So we'll have the class implement an interface. Note that, in our Java source code,
 * there won't be any classes that implement this interface, but we can write to it and call its methods.
 */
public interface ICalculator {
    int add(int left, int right);
}
