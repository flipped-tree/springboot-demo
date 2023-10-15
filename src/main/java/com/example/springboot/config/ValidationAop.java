package com.example.springboot.config;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xingce
 * @date 2020/12/19 12:32
 */
@Component
@Aspect
public class ValidationAop {

    @Pointcut("@annotation(com.example.springboot.annotation.Validation)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println(JSONUtil.toJsonStr(joinPoint));
    }

}
