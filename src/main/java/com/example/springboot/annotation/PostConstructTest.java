package com.example.springboot.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xingce
 * @date 2020/7/3 1:14
 */
@Component
public class PostConstructTest {

    @PostConstruct
    public void init() {
        System.out.println("post construct 初始化");
    }

}
