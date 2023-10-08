package com.example.pattern.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * PECS(producer extends consumer super)
 *
 * @author xingce
 * @date 2023/8/5 16:00
 */
@Component
public class PipelineExecutor {
    private Map<Class<? extends PipelineContext>,
            List<? extends ContextHandler<? super PipelineContext>>> pipelineRouteMap;

    @Autowired
    public void setPipelineRouteMap(Map<Class<? extends PipelineContext>,
            List<? extends ContextHandler<? super PipelineContext>>> pipelineRouteMap) {
        this.pipelineRouteMap = pipelineRouteMap;
    }

    public boolean execute(PipelineContext context) {
        Class<? extends PipelineContext> pipelineClass = context.getClass();
        List<? extends ContextHandler<? super PipelineContext>> contextHandlers = pipelineRouteMap.get(pipelineClass);
        for (ContextHandler<? super PipelineContext> handler : contextHandlers) {
            handler.handle(context);
        }
        return false;
    }
}
