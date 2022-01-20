package com.dhy_zk.financialSystem;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 大忽悠
 * @create 2022/1/19 17:34
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dhy_zk.financialSystem.mapper")
public class Main
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}
