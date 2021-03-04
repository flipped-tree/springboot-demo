package com.example.springboot.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 对于@Autowired声明的数组、集合类型，spring并不是根据beanName去找容器中对应的bean，
 * 而是把容器中所有类型与集合（数组）中元素类型相同的bean构造出一个对应集合，注入到目标bean中
 *
 * @author xingce
 * @date 2021/3/4 12:06
 */
@Component
public class StrategyFactory {
    @Resource
    Map<String, Strategy> strategyMap;

    public Strategy getByName(String name) {
        return strategyMap.get(name);
    }
}
