package com.example.demo.config;

import io.openmessaging.storage.dledger.DLedgerConfig;
import io.openmessaging.storage.dledger.DLedgerServer;
import io.openmessaging.storage.dledger.client.DLedgerClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author roboslyq
 * @Desc
 * @create 2020-06-26 23:18
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "dledger")
public class DLedgerConfigForSpring {
    public static final String TEST_BASE = File.separator + "tmp" + File.separator + "dledgerteststore";

    String peers;

    String group;

    String selfId;

    @Bean
    public DLedgerServer initDLedgerServer(){
        DLedgerConfig config = new DLedgerConfig();
        config.setStoreBaseDir(TEST_BASE + File.separator + group);
        config.group(group).selfId(selfId).peers(peers);
        config.setStoreType(DLedgerConfig.MEMORY);
        DLedgerServer dLedgerServer = new DLedgerServer(config);
        dLedgerServer.startup();
        return dLedgerServer;
    }

    @Bean
    public DLedgerClient launchClient() {
        DLedgerClient dLedgerClient = new DLedgerClient(group, peers);
        dLedgerClient.startup();
        return dLedgerClient;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }
}
