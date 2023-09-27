package com.example.base;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDemo {

    static class Person {
        String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Stream.of(1, 2, 3)
                .peek(e -> e = e + 1)
                .collect(Collectors.toList()));

        Stream.of(1, 2, 3)
                .map(e -> e + 1)
                .forEach(e -> System.out.println("map modified" + e));
    }

    static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    enum Operation {
        ADD("+", Double::sum);
        private String symbol;
        private DoubleBinaryOperator function;

        Operation(String symbol, DoubleBinaryOperator function) {
            this.symbol = symbol;
            this.function = function;
        }

        double apply(double x, double y) {
            return function.applyAsDouble(x, y);
        }
    }
}
