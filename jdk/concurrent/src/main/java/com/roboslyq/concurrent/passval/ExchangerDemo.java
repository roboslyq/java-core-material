package com.roboslyq.concurrent.passval;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Java 通过 Exchanger 在不同Thread 之间传值
 */
public class ExchangerDemo {
    //定义数据缓冲区大小，即数据量超过此值时，进行数据传输。转输给消费者消费。
    private static final Integer DATA_SIZE = 2;
    //数据交换器
    private Exchanger<List<String>> exchanger = new Exchanger<>();
    //空缓存区
    private List<String> initialEmptyBuffer =  new ArrayList<>();
    //满缓冲区
    private List<String> initialFullBuffer =  new ArrayList<>();
    /**
     * 定义内部类：生产者，往空缓存区生产消息
     */
    class FillingLoop implements Runnable {
      public void run() {
         List<String> currentBuffer = initialEmptyBuffer;
        try {
            //一直自旋，模擬生產者进行消息生产
              while (currentBuffer != null) {
                  int currentBufferSize = currentBuffer.size();
                  //生产消息，即往currentBuffer添加消息
                  String printStr1 = "[ 生产者队列大小 ]：" + currentBufferSize;
                    addToBuffer(currentBuffer);
                    //休眠1s，模拟生产耗时
                    Thread.sleep(1000);
                    //如果消息达到阀值，则通过Exchange交换数据，让消费者进行消费。
                    if (currentBuffer.size() == DATA_SIZE){
                        String printStr = "[ 生产者队列大小 ]： " + DATA_SIZE + "开始交换数据给消费者使用" ;
                        currentBuffer = exchanger.exchange(currentBuffer);
                    }
          }
        } catch (InterruptedException ignored) {
        }
      }

        /**
         * 生产消息
         * @param currentBuffer
         */
      private void addToBuffer(List<String> currentBuffer){
          Random random = new Random(System.currentTimeMillis());
          String message =  String.valueOf(random.nextInt(10));
          String printStr = "[ 生产者：" + Thread.currentThread().getName() + "生产消息 ]： " + message ;
          currentBuffer.add( message);
          System.out.println(printStr);
      }
    }
    /**
     * 定义内部类：消费者，从Full缓存区消费消息
     */
  class EmptyingLoop implements Runnable {
      public void run() {
         List<String> currentBuffer = initialFullBuffer;
          while (currentBuffer != null) {
            //消费消息
            takeFromBuffer(currentBuffer);
            //如果消息已经完成消费，重新交换给生产者一个空的缓存区，让生产者继承生产。
            if (currentBuffer.isEmpty()) {
                try {
                    currentBuffer = exchanger.exchange(currentBuffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
              System.out.println(" ------------------------------------------");
          }
      }

      void takeFromBuffer(List<String> currentBuffer){
          currentBuffer.forEach(str->{
              String currentThread = "[ 消费线程：" + Thread.currentThread().getName() + " ] " ;
              System.out.println(currentThread  + "原thread 值：" + str);
          });
          currentBuffer.clear();
      }
    }
    private void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptyingLoop()).start();
    }

    public static void main(String[] args) {
        ExchangerDemo exchangerDemo = new ExchangerDemo();
        exchangerDemo.start();
    }

}
