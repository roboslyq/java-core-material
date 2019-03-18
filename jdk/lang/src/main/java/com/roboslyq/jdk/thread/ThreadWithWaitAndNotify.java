package com.roboslyq.jdk.thread;

public class ThreadWithWaitAndNotify {
    public static void main(String[] args) {

        SimpleContainer simpleContainer = new SimpleContainer();

        Thread thread1 =  new Thread( new Procedure(simpleContainer));

     Thread thread2 =   new Thread(new Consumer(simpleContainer));
     thread1.start();
     thread2.start();
    }
}

class Procedure implements Runnable{
    private SimpleContainer simpleContainer;
    public Procedure(SimpleContainer simpleContainer) {
        this.simpleContainer = simpleContainer;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            synchronized (simpleContainer){
                while (simpleContainer.size() == 5){
//                       simpleContainer.wait();
                        simpleContainer.notifyAll();
                        return;
                }
            }
            simpleContainer.add(i);
            System.out.println("add " + i + " data");
            i++;
        }
    }
}

class Consumer implements Runnable{
    private SimpleContainer simpleContainer;
    public Consumer(SimpleContainer simpleContainer) {
        this.simpleContainer = simpleContainer;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (simpleContainer){
                System.out.println("simpleContainer size = "+simpleContainer.size());
                return;
                }
            }

        }
}