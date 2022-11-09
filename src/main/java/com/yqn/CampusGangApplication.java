package com.yqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yqn.mapper")
@SpringBootApplication
public class CampusGangApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusGangApplication.class, args);
    }

}
