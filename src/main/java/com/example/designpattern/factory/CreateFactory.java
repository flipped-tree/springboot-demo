package com.example.designpattern.factory;

/**
 * @author xingce
 * @date 2021/9/30 16:59
 */
public class CreateFactory {

    public static <Input, Output, T extends Context<Input, Output>> T create(Input input, Output output, Class<T> responseClazz) {
        T context = null;
        try {
            context = responseClazz.newInstance();
            context.setInput(input);
            context.setOutput(output);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return context;
    }

}
