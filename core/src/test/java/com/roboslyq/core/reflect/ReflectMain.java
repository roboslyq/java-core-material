package com.roboslyq.core.reflect;

import com.roboslyq.core.common.ParentInterface;
import com.roboslyq.core.common.ParentInterfaceImpl;

import javax.security.auth.Subject;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;
//import sun.misc.ProxyGenerator;

public class ReflectMain {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//        ParentInterfaceImpl parentInterfaceImpl = new ParentInterfaceImpl();
//        ParentInterface parentInterface = (ParentInterface) Proxy.newProxyInstance(parentInterfaceImpl.getClass().getClassLoader()
//                ,parentInterfaceImpl.getClass().getInterfaces()
//                ,new ReflectImpl(parentInterfaceImpl));
//
//        parentInterface.sayHello();

        /**
         * 通用代理
         */
        ParentInterface common = (ParentInterface) Proxy.newProxyInstance(ReflectMain.class.getClassLoader()
                ,new Class[]{ParentInterface.class}
                ,new CommonReflectImpl());

        common.sayHello();
//        sun.misc.ProxyGenerator.saveGeneratedFiles:
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags)
//        private static final boolean saveGeneratedFiles = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"))).booleanValue();


//        try {
//            ClassFileTransformerService.writeToFile(parentInterface);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

//    public static void createProxyClassFile()   {
//        String name = "ProxySubject";
//        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
//        try{
//            FileOutputStream out = new FileOutputStream( name + ".class" );
//            out.write( data );
//            out.close();
//        }catch( Exception e ) {
//            e.printStackTrace();
//        }
//    }


//    private static class ProxyGenerator {
//    }
}
