package com.example.springboot.controller;

import com.example.springboot.aop.HttpLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HttpLogDemoController {

    @HttpLog
    @GetMapping("/http/log/demo")
    public Map<String, Object> demo(@RequestParam(defaultValue = "world") String name) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("message", "hello " + name);
        result.put("success", true);
        return result;
    }
}
