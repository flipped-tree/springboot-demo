package com.example.pattern.pipeline;

import org.springframework.stereotype.Component;

/**
 * @author xingce
 * @date 2023/8/5 14:07
 */
@Component
public class ModelInstanceSaver implements ContextHandler<InstanceBuildContext>{
    @Override
    public boolean handle(InstanceBuildContext context) {
        return false;
    }
}
