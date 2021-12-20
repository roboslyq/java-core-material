package com.roboslyq.jndi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/19 21:25
 */
public interface Service extends Remote {
    String sayHello() throws RemoteException;
}
