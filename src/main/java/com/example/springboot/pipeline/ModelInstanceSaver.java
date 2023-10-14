package com.example.springboot.pipeline;

import org.springframework.stereotype.Component;

@Component
public class ModelInstanceSaver implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        System.out.println("模型保存");
        return false;
    }
}
