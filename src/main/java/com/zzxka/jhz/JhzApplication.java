package com.zzxka.jhz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzxka.jhz.system.dao")
public class JhzApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhzApplication.class, args);
    }

}
