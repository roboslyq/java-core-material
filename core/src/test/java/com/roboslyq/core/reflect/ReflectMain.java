package com.roboslyq.core.reflect;

import com.roboslyq.core.common.CommReflect;
import com.roboslyq.core.common.ParentInterface;
import com.roboslyq.core.common.ParentInterfaceImpl;

import java.lang.reflect.Proxy;

public class ReflectMain {
    public static void main(String[] args) {
//        ParentInterfaceImpl parentInterfaceImpl = new ParentInterfaceImpl();
//        ParentInterface parentInterface = (ParentInterface) Proxy.newProxyInstance(parentInterfaceImpl.getClass().getClassLoader()
//                ,parentInterfaceImpl.getClass().getInterfaces()
//                ,new ReflectImpl(parentInterfaceImpl));
//
//        parentInterface.sayHello();
//
//        CommReflect commReflect = new CommReflect();
//        ParentInterface parentInterface = (ParentInterface) Proxy.newProxyInstance(parentInterfaceImpl.getClass().getClassLoader()
//                ,parentInterfaceImpl.getClass().getInterfaces()
//                ,new ReflectImpl(parentInterfaceImpl));
//
//        parentInterface.sayHello();




//        sun.misc.ProxyGenerator.saveGeneratedFiles:
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags)
//        private static final boolean saveGeneratedFiles = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"))).booleanValue();


//        try {
//            ClassFileTransformerService.writeToFile(parentInterface);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static class ProxyGenerator {
    }
}
