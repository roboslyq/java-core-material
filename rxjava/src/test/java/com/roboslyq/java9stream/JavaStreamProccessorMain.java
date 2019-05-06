/**
 * Copyright (C), 2015-2019
 * FileName: JavaStreamApiMain1
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:36
 * Description: java stream api测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:36      1.0.0               创建
 */
package com.roboslyq.java9stream;

/**
 *
 * 〈java stream api测试〉
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class JavaStreamProccessorMain {
    public static void main(String[] args) throws InterruptedException {
        //创建 processor处理
        Java9StreamProcessor processor = new Java9StreamProcessor( simpleDto ->{
            SimpleDto1 simpleDto1 = new SimpleDto1();
            simpleDto1.setId(simpleDto.getId());
            simpleDto1.setName("simpleDto1-" + simpleDto.getName());
            return new SimpleDto1();
        });
        //创建订阅者
        SimpleDto1Suscriber  suscriber = new SimpleDto1Suscriber();
        //订阅信息
        processor.subscribe(suscriber);
        //构造数据源并且发布消息
        SimpleDtoHelper.getSimpleDto1s(10).forEach(processor::submit);
        //休眠等待异步任务完成
        Thread.sleep(1000);
        processor.close();
    }

}