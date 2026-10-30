package com.hxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hxx.mapper")
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        System.out.println("启动成功：" + environment.getProperty("server.port"));
    }
}
