package com.example.springboot.pipeline;

import org.springframework.stereotype.Component;

@Component
public class ModelInstanceCreator implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        System.out.println("模型创建");
        return true;
    }
}
