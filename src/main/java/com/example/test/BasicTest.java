package com.example.test;

import java.util.*;

public class BasicTest {

    public static void main(String[] args) {
//        Person p = new Person();
//        p.setAge("12");
//        p.setName("Henry");
//
//        Person personA = p;
//
//        personA.setName("Lily");
//
//        System.out.println(p.getName());

//        List<Integer> integers = List.of(1, 2, 3, 4);
//        List<Long> longs = List.of(1L,2L);
//        produce(integers);
//        produce(longs);

//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1, 1);
//        Integer put = map.put(1, 2);
//        System.out.println(put);
//        map.forEach((key, value) -> {
//            System.out.println(key + "" + value);
//        });
//        List<Integer> socks = List.of(1, 2, 1, 2, 1, 3, 2);
//        int i = sockMerchant(7, socks);
//        System.out.println(i);

//        fizzBuzz(15);

//        int[] arr = {5, 4, 3, 2, 1};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

//        List<List<String>> partition = partition("aab");
//        System.out.println(partition);

//        int[][] ints = new int[][]{{5, 2}, {3, 4}};
//        Arrays.sort(ints, Comparator.comparingInt(arr -> arr[0]));
//        System.out.println(ints[0][0]);
//        System.out.println(ints[0][1]);
//        System.out.println(ints.length);

        String t = "Abc";
        int[] tCount = new int[128];
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }
        System.out.println(Arrays.toString(tCount));
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = getMiddle(arr, left, right);
            quickSort(arr, 0, middle - 1);
            quickSort(arr, middle + 1, right);
        }
    }

    static int getMiddle(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }

    static <T> void write(T t) {

    }

    static void produce(List<? extends Number> list) {
        for (Number i : list) {
            System.out.println(i);
        }
    }

    static void consume(List<? super Integer> list) {
        list.add(1);
    }

    static class Person {
        String name;
        String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        if (ar == null || ar.isEmpty()) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : ar) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int pairs = 0;
        for (Integer value : map.values()) {
            pairs += value / 2;
        }
        return pairs;
    }

    public static void fizzBuzz(int n) {
        // Write your code here
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
                continue;
            }
            if (i % 3 == 0) {
                System.out.println("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                System.out.println("Buzz");
                continue;
            }
            System.out.println(i);
        }
    }

    static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        Deque<String> path = new LinkedList<>();
        backtrace(s, 0, path, res);
        return res;
    }

    static void backtrace(String s, int startIndex, Deque<String> path, List<List<String>> res) {
        //如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            //如果是回文子串，则记录
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i + 1);
                path.addLast(str);
            } else {
                continue;
            }
            //起始位置后移，保证不重复
            backtrace(s, i + 1, path, res);
            path.removeLast();
        }
    }

    static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}