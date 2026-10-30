package com.hxx;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GateWayApplication.class);
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GateWayApplication.class);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        LOGGER.info("网关启动成功！");
        LOGGER.info("网关地址: \t http://127.0.0.1:{}",environment.getProperty("server.port"));
    }
}
