package com.example.springboot.pipeline;

import java.time.LocalDateTime;

public class PipelineContext {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
