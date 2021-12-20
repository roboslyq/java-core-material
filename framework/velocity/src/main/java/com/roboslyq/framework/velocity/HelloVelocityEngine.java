/**
 * Copyright (C), 2015-2021
 * FileName: HelloVelocity
 * Author:   luo.yongqian
 * Date:     2021/9/25 11:14
 * Description: velocity Demo
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/9/25 11:14      1.0.0               创建
 */
package com.roboslyq.framework.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 〈velocity Demo〉
 * @author roboslyq
 * @date 2021/9/25
 * @since 1.0.0
 */
public class HelloVelocityEngine {
    public static void main(String[] args) {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        // 获取模板文件
        Template t = ve.getTemplate("templates/hellovelocity.vm");
        // 设置变量
        VelocityContext ctx = new VelocityContext();
            ctx.put("name", "Velocity");
        List list = new ArrayList();
            list.add("1");
            list.add("2");
            list.add("3");
            ctx.put("list", list);

            ctx.put("dateUtils",new DateUtils());
        // 输出
        StringWriter sw = new StringWriter();
        t.merge(ctx,sw);
        System.out.println(sw.toString());
    }

}