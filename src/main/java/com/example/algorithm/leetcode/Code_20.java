package com.example.algorithm.leetcode;

import java.util.Stack;

public class Code_20 {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
    }

    private static boolean isValid(String str) {
        int length = str.length();
        if (length == 0 || length % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character top = stack.peek();
                if ((top == '(' && c == ')') || top == '{' && c == '}' || top == '[' && c == ']') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
