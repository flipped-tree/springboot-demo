package com.example.test;

public class BigNumberAdd {

    public static void main(String[] args) {
        String s1 = "21543655";
        String s2 = "4332656442";
        System.out.println("输入: " + s1 + ", " + s2);
        System.out.println("输出: " + addStrings(s1, s2));
    }

    static String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            int sum = digit1 + digit2 + carry;

            carry = sum / 10;

            result.append(sum % 10);

            i--;
            j--;
        }

        return result.reverse().toString();
    }
}
