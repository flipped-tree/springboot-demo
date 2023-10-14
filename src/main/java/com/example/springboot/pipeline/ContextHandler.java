package com.example.springboot.pipeline;

public interface ContextHandler<T extends PipelineContext> {
    boolean handle(T context);
}
