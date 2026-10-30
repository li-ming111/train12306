package com.hxx;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hxx.mapper")
public class MemberApplication {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("启动成功！！");
        log.info("地址: \thttp://127.0.0.1:{}",env.getProperty("server.port"));
    }
}
