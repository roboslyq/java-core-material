package com.roboslyq.jndi.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/19 21:27
 */
public class RMIServerMain {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("hello", new ServiceImpl());
        Thread.currentThread().join();
    }
}
