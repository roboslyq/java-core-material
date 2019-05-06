/**
 * Copyright (C), 2015-2019
 * FileName: Java9StreamProcessor
 * Author:   luo.yongqian
 * Date:     2019/5/6 17:27
 * Description: Java stream中信息转换实现
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 17:27      1.0.0               创建
 */
package roboslyq.java9stream;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
/**
 *
 * 〈Java stream中信息转换实现〉
 * {@link Processor}接口同时扩展了{@link Flow.Publisher}和{@link Flow.Subscriber}接口，
 * 用于在发布者和订阅者之间转换消息。
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class Java9StreamProcessor extends SubmissionPublisher<SimpleDto1> implements Processor<SimpleDto,SimpleDto1> {

    /**
     * 发布者和订阅者之间创建异步非阻塞链接。
     * 订阅者调用其request方法来向发布者请求项目。
     * 订阅者调用其cancel取消订阅的方法，即关闭发布者和订阅者之间的链接。
     */
    private Subscription subscription;

    /**
     * 转换lambda实现
     */
    private Function<SimpleDto,SimpleDto1> function;

    /**
     * 构造函数，实现lambda转换
     * @param function
     */
    public Java9StreamProcessor(Function<SimpleDto,SimpleDto1> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(SimpleDto dto) {
        submit((SimpleDto1) function.apply(dto));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}