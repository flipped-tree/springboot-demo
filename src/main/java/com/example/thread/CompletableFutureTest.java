package com.example.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    public static void main(String[] args) {
//        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 1);
//        int i;
//        try {
//            i = cf.get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(i);

        Person tom = new Person("Tom");
        Person lily = new Person("Lily");
        swap(tom, lily);
        System.out.println("tom:" + tom.getName());
        System.out.println("lily:" + lily.getName());
    }

    private static void swap(Person tom, Person lily) {
        Person temp = tom;
        tom = lily;
        lily = temp;
        System.out.println("first:" + tom.getName());
        System.out.println("second:" + lily.getName());
    }

    static class Person {

        public Person(String name) {
            this.name = name;
        }

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
