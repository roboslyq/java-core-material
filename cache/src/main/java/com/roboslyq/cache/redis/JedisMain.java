/**
 * Copyright (C), 2015-2020
 * FileName: JedisMain
 * Author:   luo.yongqian
 * Date:     2020/4/24 17:46
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/24 17:46      1.0.0               创建
 */
package com.roboslyq.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/4/24
 * @since 1.0.0
 */
public class JedisMain {
    public static void main(String[] args) {
        JedisConfig jedisConfig = new JedisConfig("47.93.201.88",6380,"redis@88@lyq");
        normal(jedisConfig);
        cluster(jedisConfig);
    }

    private static void normal(JedisConfig jedisConfig) {
        Jedis jedis = new Jedis(jedisConfig.getIp(),jedisConfig.getPort());
        jedis.auth(jedisConfig.getAuth());
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

    public static void cluster(JedisConfig jedisConfig ){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort(jedisConfig.getIp(), jedisConfig.getPort()));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,10,10,5,"redis@88@lyq",new GenericObjectPoolConfig());
        jc.set("foo", "bar");

        String value = jc.get("foo");
    }

}