package com.roboslyq.jndi.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/19 21:26
 */
public class ServiceImpl implements Service, Serializable {
    @Override
    public String sayHello() throws RemoteException {
        return "roboslyq";
    }
    protected ServiceImpl() throws RemoteException {
    }
}
