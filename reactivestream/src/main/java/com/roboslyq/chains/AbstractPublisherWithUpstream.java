/**
 * Copyright (C), 2015-2019
 * FileName: AbstractPublisherWithUpstream
 * Author:   luo.yongqian
 * Date:     2019/6/26 13:11
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/26 13:11      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/26
 * @since 1.0.0
 */
public abstract class AbstractPublisherWithUpstream<T,U> extends AbstractPublisher<U> implements HasUpstreamPublisher<T> {

    final Publisher<T> previous;

    AbstractPublisherWithUpstream(Publisher<T> previous) {
        this.previous = previous;
    }

    @Override
    public Publisher<T> previous() {
        return previous;
    }
}