package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xingce
 *
 * 使用非内置tomcat 继承 SpringBootServletInitializer，实现confirure方法
 */
@SpringBootApplication(scanBasePackages = "com.example.springboot.*")
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
