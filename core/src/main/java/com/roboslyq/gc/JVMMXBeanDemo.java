package com.roboslyq.gc;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
public class JVMMXBeanDemo {
    public static void main(String[] args) {
        //内存相关
        printMemery();
        //运行时参数相关(JVM参数)
        printRuntime();
        //获取当前操作系统相关
        printOperatingSystem();
        //线程相关
        printThread();
        //GC
        printGC();
//        //==========================Compilation=========================
//        System.out.println("==========================Compilation=========================");
//        CompilationMXBean compilMBean=(CompilationMXBean)ManagementFactory.getCompilationMXBean();
//        System.out.println("getName() " + compilMBean.getName());
//        System.out.println("getTotalCompilationTime() " + compilMBean.getTotalCompilationTime());
//        //==========================MemoryPool=========================
//        System.out.println("==========================MemoryPool=========================");
//        //获取多个内存池的使用情况
//        List<MemoryPoolMXBean> mpMBeanList= ManagementFactory.getMemoryPoolMXBeans();
//        for(MemoryPoolMXBean mpMBean : mpMBeanList){
//            System.out.println("getUsage() " + mpMBean.getUsage());
//            System.out.println("getMemoryManagerNames() "+ mpMBean.getMemoryManagerNames().toString());
//        }
//
//        //==========================Other=========================
//        System.out.println("==========================Other=========================");
//        //Java 虚拟机中的内存总量,以字节为单位
//        int total = (int)Runtime.getRuntime().totalMemory()/1024/1024;
//        System.out.println("内存总量 ：" + total + "mb");
//        int free = (int)Runtime.getRuntime().freeMemory()/1024/1024;
//        System.out.println("空闲内存量 ： " + free + "mb");
//        int max = (int) (Runtime.getRuntime().maxMemory() /1024 / 1024);
//        System.out.println("最大内存量 ： "  + max + "mb");

    }

    /**
     *打印内存
     */
    public static void printMemery(){
        MemoryMXBean heapMemoryMXBean = ManagementFactory.getMemoryMXBean();
        //堆内存使用情况

        MemoryUsage heapMemoryUsage = heapMemoryMXBean.getHeapMemoryUsage();
        System.out.println("堆内存----start----------------->");
        System.out.println("初始内存大小(M)" + heapMemoryUsage.getInit()/(1024*1024));
        System.out.println("最大内存大小(M)" + heapMemoryUsage.getMax()/(1024*1024));
        System.out.println("已使用内存大小(M)" + heapMemoryUsage.getUsed()/(1024*1024));
        System.out.println("Committed内存大小(M)" + heapMemoryUsage.getCommitted()/(1024*1024));
        System.out.println("堆内存----end----------------->");
        System.out.println();
        //非堆内存使用情况
        MemoryUsage nonHeapMemoryUsage =  heapMemoryMXBean.getNonHeapMemoryUsage();
        System.out.println("非堆内存----start----------------->");
        System.out.println("(非堆)初始内存大小(M)" + nonHeapMemoryUsage.getInit()/(1024*1024));
        System.out.println("(非堆)最大内存大小(M)" + nonHeapMemoryUsage.getMax()/(1024*1024));
        System.out.println("(非堆)已使用内存大小(M)" + nonHeapMemoryUsage.getUsed()/(1024*1024));
        System.out.println("(非堆)Committed内存大小(M)" + nonHeapMemoryUsage.getCommitted()/(1024*1024));
        System.out.println("非堆内存----end----------------->");
    }

    public static void printRuntime(){
        System.out.println();
        System.out.println("Runtime----start----------------->");
        RuntimeMXBean runtimeMBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("JVM name : " + runtimeMBean.getVmName());
        System.out.println("lib path : " + runtimeMBean.getLibraryPath());
        System.out.println("class path : " + runtimeMBean.getClassPath());
        System.out.println("Vm Version : " + runtimeMBean.getVmVersion());
        //java options
        List<String> argList = runtimeMBean.getInputArguments();
        for(String arg : argList){
            System.out.println("arg : " + arg);
        }
        System.out.println("Runtime----end----------------->");
    }

    public static void printOperatingSystem(){
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println();
         System.out.println("OperatingSystem----start----------------->");
        //获取操作系统相关信息
        System.out.println("getName() "+ operatingSystemMXBean.getName());
        System.out.println("getVersion() " + operatingSystemMXBean.getVersion());
        System.out.println("getArch() "+operatingSystemMXBean.getArch());
        System.out.println("getAvailableProcessors() " + operatingSystemMXBean.getAvailableProcessors());
    }

    public static void printThread(){
        System.out.println();
        System.out.println("Thread----start----------------->");
        //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        ThreadMXBean threadMBean= ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount() " + threadMBean.getThreadCount());
        System.out.println("getPeakThreadCount() " + threadMBean.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime() " + threadMBean.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount() " + threadMBean.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime() "+ threadMBean.getCurrentThreadUserTime());
    }

    public static void printGC(){
        System.out.println();
        System.out.println("GC(GarbageCollector)----start----------------->");
        //获取GC的次数以及花费时间之类的信息
        List<GarbageCollectorMXBean> gcMBeanList=ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean gcMBean : gcMBeanList){
            System.out.println("getName() " + gcMBean.getName());
            System.out.println("getMemoryPoolNames() "+ gcMBean.getMemoryPoolNames());
        }
    }
}
