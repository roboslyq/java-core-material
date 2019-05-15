/**
 * Copyright (C), 2015-2019
 * FileName: JavaFlowApiMain1
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:36
 * Description: java stream api测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:36      1.0.0               创建
 */
package com.roboslyq.java9flowapi;

import com.roboslyq.java9flowapi.dto.SimpleDto1;

/**
 *
 * 〈java stream api测试〉
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class JavaFlowProccessorMain {
    public static void main(String[] args) throws InterruptedException {
        //创建 processor处理
        FlowProcessorDemo processor = new FlowProcessorDemo(simpleDto ->{
            SimpleDto1 simpleDto1 = new SimpleDto1();
            simpleDto1.setId(simpleDto.getId());
            simpleDto1.setName("simpleDto1-" + simpleDto.getName());
            return new SimpleDto1();
        });
        //创建订阅者
        SuscriberDemo suscriber = new SuscriberDemo();
        //订阅信息
        processor.subscribe(suscriber);
        //构造数据源并且发布消息
        DtoHelper.getSimpleDto1s(10).forEach(processor::submit);
        //休眠等待异步任务完成
        Thread.sleep(1000);
        processor.close();
    }

}