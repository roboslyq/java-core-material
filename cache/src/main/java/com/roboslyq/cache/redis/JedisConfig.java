/**
 * Copyright (C), 2015-2020
 * FileName: JedisConfig
 * Author:   luo.yongqian
 * Date:     2020/4/24 17:45
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/24 17:45      1.0.0               创建
 */
package com.roboslyq.cache.redis;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/4/24
 * @since 1.0.0
 */
public class JedisConfig {

    private String ip;
    private int port = 6379;
    private String auth;

    public JedisConfig() {

    }

    public JedisConfig(String ip) {
        this.ip = ip;
    }

    public JedisConfig(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public JedisConfig(String ip, int port, String auth) {
        this.ip = ip;
        this.port = port;
        this.auth = auth;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}