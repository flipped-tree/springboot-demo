package com.example.test;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid(""));
        System.out.println(isValid("{}"));
        System.out.println(isValid("{[}]"));
        System.out.println(isValid("()[]{}"));
    }

    static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != ch) {
                return false;
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}
