/**
 * Copyright (C), 2015-2020
 * FileName: SqlFactoryWithoutXmlBootstrap
 * Author:   luo.yongqian
 * Date:     2020/6/28 17:27
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/6/28 17:27      1.0.0               创建
 */
package com.roboslyq.framework.mybatis;

import com.roboslyq.framework.mybatis.domain.Blog;
import com.roboslyq.framework.mybatis.mapper.BlogMapper;
import com.roboslyq.framework.mybatis.mapper.BlogMapperNoXML;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 *
 * 〈此时，BlogMapper中的相关方法需要@Select注解标识，具体值为具体的SQL〉
 * @author roboslyq
 * @date 2020/6/28
 * @since 1.0.0
 */
public class SqlFactoryWithoutXmlBootstrap {
    public static void main(String[] args) {
        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapperNoXML.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapperNoXML mapper = session.getMapper(BlogMapperNoXML.class);
            Blog blog = mapper.selectBlog(1);
            System.out.println(blog.getId());
            System.out.println(blog.getTitle());
        }
    }
}