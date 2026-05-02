package com.example.springboot.demo;

import com.example.springboot.pipeline.InstanceBuildContext;
import com.example.springboot.pipeline.PipelineExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Resource
    PipelineExecutor pipelineExecutor;

    @Test
    public void testPipeline() {
        InstanceBuildContext context = new InstanceBuildContext();
        pipelineExecutor.accept(context);
    }
}
