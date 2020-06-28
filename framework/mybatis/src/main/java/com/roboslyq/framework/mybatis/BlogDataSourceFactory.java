/**
 * Copyright (C), 2015-2020
 * FileName: BlogDataSourceFactory
 * Author:   luo.yongqian
 * Date:     2020/6/28 17:33
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/6/28 17:33      1.0.0               创建
 */
package com.roboslyq.framework.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 * 〈可以参看mybatis.jar 中 org.apache.ibatis.datasource 下的相关代码〉
 * @author roboslyq
 * @date 2020/6/28
 * @since 1.0.0
 */
public class BlogDataSourceFactory {
    public static DataSource getBlogDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://47.93.201.88:3306/test");
        properties.setProperty("username", "test_user");
        properties.setProperty("password", "1q2w3e4r(A");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        return dataSource;
    }
}