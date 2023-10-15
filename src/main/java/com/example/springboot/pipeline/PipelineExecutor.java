package com.example.springboot.pipeline;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class PipelineExecutor {
    @Resource
    Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? super PipelineContext>>> pipelineRouteMap;

    public boolean accept(PipelineContext context) {
        Class<? extends PipelineContext> dataType = context.getClass();
        List<? extends ContextHandler<? super PipelineContext>> contextHandlers = pipelineRouteMap.get(dataType);
        if (CollUtil.isEmpty(contextHandlers)) {
            return false;
        }
        boolean handleResult = true;
        for (ContextHandler<? super PipelineContext> contextHandler : contextHandlers) {
            handleResult = contextHandler.handle(context);
            if (!handleResult) {
                break;
            }
        }
        return handleResult;
    }
}
