package com.tsy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        log.info("启动项目666");
        System.out.println("当前激活配置: " + System.getProperty("spring.profiles.active"));

        SpringApplication.run(ServerApplication.class, args);

    }

}
