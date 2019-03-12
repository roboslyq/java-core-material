package com.roboslyq.jdk.lang;

public class ThreadLocalTest {

    private final static ThreadLocal<String> THREAD_LOCAL_1 = new ThreadLocal<String>();
    private final static ThreadLocal<String> THREAD_LOCAL_2 = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        //模拟不同的线程赋值和取值
        for(int i=0;i<2;i++){
            new Thread(
                    new Runnable() {
                        public void run() {
                            ThreadLocalTest.getThreadLocal1().set("currentThead -- THREAD_LOCAL_1 -- "
                                    + Thread.currentThread().getId());
                            ThreadLocalTest.getThreadLocal2().set("currentThead -- THREAD_LOCAL_2 -- "
                                    + Thread.currentThread().getName());
                            Print print = new Print();
                            print.print();
                        }
                    }
            ).start();
        }

        Thread.sleep(1000);
    }


    public static ThreadLocal<String> getThreadLocal1() {
        return THREAD_LOCAL_1;
    }
    public static ThreadLocal<String> getThreadLocal2() {
        return THREAD_LOCAL_2;
    }

}

//单独一个类，模拟取值
class Print{
    //从ThreadLocal载体(ThreadLocalTest)中，获取对应的ThreadLocal保持的值
    public void print(){
        System.out.println(ThreadLocalTest.getThreadLocal1().get());
        System.out.println(ThreadLocalTest.getThreadLocal2().get());
    }
}