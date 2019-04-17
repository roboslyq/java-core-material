package com.roboslyq.jdk.collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<DelayDto> delayQueue = new DelayQueue();
        new Thread(()->{
            for(int i = 0; i < 100; i++){
                DelayDto delayDto = new DelayDto(getDelayTime());
                delayDto.setId(i);
                delayQueue.offer(delayDto);
                System.out.println("offer :" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
           for(;;){
               try {
                   DelayDto delayDto = delayQueue.take();
                   System.out.println("consumer : " + delayDto.getId());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();

    }

    /**
     * 获取延迟时间
     *
     * @return 延迟后将来时间戳(ms)
     */
    public static Long getDelayTime(){
        //延迟3s钟
        return System.currentTimeMillis() + 3000;
    }
}

class DelayDto implements Delayed {
    int id;
    Long createdTimestamp;

    public DelayDto(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        /**
         * unit.convert 方法第一个参数为数字，第二个参数为数字代表的时间单位，这里作为毫秒
         * 执行convert后，返回unit当前的时间单位，此处是纳秒（在DelayQueue中传参为ＮＡＮＯＳＥＣＯＮＤＳ）
         * 如果第二个参数写成纳秒，TimeUnit.NANOSECONDS,则这个转换函数转换前后数值相等，但是DelayQueue的take方法中
         * 　if (delay > 0) {
         *      long tl = available.awaitNanos(delay);
         * 　}
         * 等待的时间将缩短，上面的for循环重复执行将近原来的1000倍。这样会造成CPU空转，是load升高。
         */
        return unit.convert(this.createdTimestamp - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
