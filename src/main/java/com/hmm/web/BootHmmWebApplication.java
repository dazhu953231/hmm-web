package com.hmm.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hmm.web.dao")
public class BootHmmWebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootHmmWebApplication.class, args);
    }
    
}
