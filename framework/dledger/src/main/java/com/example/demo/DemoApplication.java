package com.example.demo;

import io.openmessaging.storage.dledger.DLedgerServer;
import io.openmessaging.storage.dledger.MemberState;
import io.openmessaging.storage.dledger.client.DLedgerClient;
import io.openmessaging.storage.dledger.protocol.AppendEntryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	private static int serverPort = 0;
	@Autowired
    DLedgerServer dLedgerServer;

	@Autowired
    DLedgerClient dLedgerClient;
	@GetMapping("/status")
    MemberState status(){
		return dLedgerServer.getMemberState();
	}
    @GetMapping("/getdata")
	public String getData(Long index){
	   return new String(dLedgerServer.getdLedgerStore().get(index).getBody());
    }
    @GetMapping("/setdata")
    public AppendEntryResponse setData(Long index){
        AppendEntryResponse appendEntryResponse = dLedgerClient.append(("HelloSingleServerInMemory" + index).getBytes());
        return appendEntryResponse;
    }
    public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class,args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		ConfigurableApplicationContext context =builder.build().run(args);
		serverPort = Integer.parseInt(context.getEnvironment().getProperty("server.port"));
		System.out.println(serverPort);
	}

}
