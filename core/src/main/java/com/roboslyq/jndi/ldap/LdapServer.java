package com.roboslyq.jndi.ldap;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;

/**
 * @Author roboslyq
 * @desc 轻型目录访问协议（Lightweight Directory Access Protocol) LDAP目录以树状的层次结构来存储数据。
 * @since 2021/12/19 21:44
 */
public class LdapServer {
    public static void main(String[] args) throws Exception {
        // 创建LDAP服务配置
        InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=example,dc=com");
        // 配置监听基本信息
        config.setListenerConfigs(new InMemoryListenerConfig(
                "listen", //$NON-NLS-1$
                InetAddress.getByName("0.0.0.0"), //$NON-NLS-1$
                1389,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault()));

        config.setSchema(null);
        config.setEnforceAttributeSyntaxCompliance(false);
        config.setEnforceSingleStructuralObjectClass(false);
        // 创建LDAP服务
        InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
        ds.add("dn: " + "dc=example,dc=com", "k: k");
        ds.add("dn: " + "uid=kingkk,dc=example,dc=com", "k: kk");
        // 启动LDAP监听服务
        System.out.println("Listening on 0.0.0.0:" + 1389); //$NON-NLS-1$
        ds.startListening();
    }
}
