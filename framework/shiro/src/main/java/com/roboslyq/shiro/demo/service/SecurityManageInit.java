package com.roboslyq.shiro.demo.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SecurityManageInit {
    @Autowired
    private SecurityManager securityManager;
    @PostConstruct
    private void initStaticSecurityManager() {
        SecurityUtils.setSecurityManager( securityManager);
    }
}
