package com.example.springboot.pipeline;

import org.springframework.stereotype.Component;

@Component
public class InputDataPreChecker implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        System.out.println("表单校验");
        return true;
    }
}
