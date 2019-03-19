package com.roboslyq.jdk.thread;

public class ThreadCommonTest {

    static volatile SimpleContainer simpleContainer = new SimpleContainer();

    public static void main(String[] args) {

        Thread thread1 =  new Thread( ()->{
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("i am  interrupted!!");
                    return;
                }
                simpleContainer.add(i);
                System.out.println("add " + i + " data");
            }
        } );

     Thread thread2 =   new Thread( ()->{
                for(;;){
                    if(simpleContainer.size() ==5){
                        System.out.println("size == 5, finished!");
                        thread1.interrupt();
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        } );
     thread1.start();
     thread2.start();
    }
}
