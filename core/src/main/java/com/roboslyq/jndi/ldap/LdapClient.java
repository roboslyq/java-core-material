package com.roboslyq.jndi.ldap;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/19 21:44
 */
public class LdapClient {
    public static void main(String[] args) throws Exception {
//        第一步：为初始化上下文选择服务提供者:使用Sun的LDAP服务提供者
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

//        提供初始化上下文需要的信息
        env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:1389");

//        第三步：创建初始化上下文
        Context ctx = new InitialContext(env);
        Object object = ctx.lookup("uid=kingkk,dc=example,dc=com");
        System.out.println(object.getClass());
    }
}
