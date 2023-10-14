package com.example.springboot.pipeline;

public class InstanceBuildContext extends PipelineContext {
    private long modelId;
    private long userId;

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return "模型实例创建上下文";
    }
}
