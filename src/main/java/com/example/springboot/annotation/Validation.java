package com.example.springboot.annotation;

import java.lang.annotation.*;

/**
 * @author xingce
 * @date 2020/12/19 12:30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validation {
}
