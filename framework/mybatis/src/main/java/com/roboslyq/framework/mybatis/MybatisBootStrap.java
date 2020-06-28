/**
 * Copyright (C), 2015-2020
 * FileName: MybatisBootStrap
 * Author:   luo.yongqian
 * Date:     2020/6/28 16:26
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/6/28 16:26      1.0.0               创建
 */
package com.roboslyq.framework.mybatis;

import com.roboslyq.framework.mybatis.domain.Blog;
import com.roboslyq.framework.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/6/28
 * @since 1.0.0
 */
public class MybatisBootStrap {


    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(1);
            System.out.println(blog.getId());
            System.out.println(blog.getTitle());
        }
    }

}