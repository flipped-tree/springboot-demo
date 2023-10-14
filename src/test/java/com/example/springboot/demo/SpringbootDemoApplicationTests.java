package com.example.springboot.demo;

import com.example.springboot.pipeline.InstanceBuildContext;
import com.example.springboot.pipeline.PipelineExecutor;
import com.example.springboot.strategy.StrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    StrategyFactory strategyFactory;
    @Resource
    PipelineExecutor pipelineExecutor;

    @Test
    public void testStrategy() {
        strategyFactory.getByName("firstStrategy").doSomething();
    }

    @Test
    public void testPipeline() {
        InstanceBuildContext context = new InstanceBuildContext();
        pipelineExecutor.accept(context);
    }
}
