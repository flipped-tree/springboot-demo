package com.example.designpattern.factory;

/**
 * @author xingce
 * @date 2021/9/30 17:01
 */
public interface Context<Input, Output> {
    void setInput(Input input);

    void setOutput(Output output);
}
