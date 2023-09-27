package com.example.pattern.pipeline;

/**
 * @author xingce
 * @date 2023/8/5 14:04
 */
public class PipelineContext {
    /**
     * @return 获取数据名称
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
