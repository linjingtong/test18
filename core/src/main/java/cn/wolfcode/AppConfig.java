package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.wolfcode.mapper"})
public class AppConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class,args);
    }
}
