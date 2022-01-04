package com.example.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author xingce
 * @date 2021/12/29 13:20
 */
public class SupplierConsumer {

    public static void main(String[] args) {
        Lambda origin = Lambda.of("origin");
        System.out.println("before: " + origin.getName());
        setPropertyIfAbsent(origin::getName, origin::setName, "target");
        System.out.println("after: " + origin.getName());
    }

    private static <T> void setPropertyIfAbsent(Supplier<T> supplier, Consumer<T> consumer, T newValue) {
        if (newValue != null && supplier.get() != null) {
            consumer.accept(newValue);
        }
    }

}
