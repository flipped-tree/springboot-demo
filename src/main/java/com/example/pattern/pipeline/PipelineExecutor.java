package com.example.pattern.pipeline;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xingce
 * @date 2023/8/5 16:00
 */
@Component
public class PipelineExecutor {
    private Map<Class<? extends PipelineContext>,
        List<? extends ContextHandler<? extends PipelineContext>>> pipelineRouteMap;

    @Autowired
    public void setPipelineRouteMap(Map<Class<? extends PipelineContext>,
        List<? extends ContextHandler<? extends PipelineContext>>> pipelineRouteMap) {
        this.pipelineRouteMap = pipelineRouteMap;
    }

    public boolean execute(PipelineContext context) {
        Class<? extends PipelineContext> pipelineClass = context.getClass();
        List<? extends ContextHandler<? extends PipelineContext>> contextHandlers = pipelineRouteMap.get(pipelineClass);
        for (ContextHandler<? extends PipelineContext> handler : contextHandlers) {

        }
        return false;
    }
}
