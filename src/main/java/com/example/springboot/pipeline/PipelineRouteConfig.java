package com.example.springboot.pipeline;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class PipelineRouteConfig implements ApplicationContextAware {

    ApplicationContext applicationContext;

    private static final Map<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<?
            extends PipelineContext>>>> PIPELINE_ROUTE_MAP = new HashMap<>();

    static {
        PIPELINE_ROUTE_MAP.put(InstanceBuildContext.class, Arrays.asList(InputDataPreChecker.class,
                ModelInstanceCreator.class, ModelInstanceSaver.class));
    }

    @Bean("pipelineRouteMap")
    public Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? extends PipelineContext>>> getHandlerPipelineMap() {
        return PIPELINE_ROUTE_MAP.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, this::toPipeline));
    }

    private List<? extends ContextHandler<? extends PipelineContext>> toPipeline(Map.Entry<Class<?
            extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> entry) {
        return entry.getValue().stream().map(s -> applicationContext.getBean(s)).collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
