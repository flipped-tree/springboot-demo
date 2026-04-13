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

        int[] nums = {1, 2, 4, 5, 6, 7};
//        int i = removeDuplicates(nums);
        int i = removeTwo(nums);
//        System.out.println(i);
//        System.out.println(Arrays.toString(nums));
        System.out.println(summaryRanges(nums));
//        int[] ints = {1, 3, 5, 4, 2};
//        nextPermutation(ints);
    }

    static void nextPermutation(int[] nums) {
        int len = nums.length;
        // 从后往前找到升序坐标
        int upIndex = -1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                upIndex = i;
                break;
            }
        }
        // 不存在即为降序 直接反转整个数组
        if (upIndex == -1) {
            revert(nums, 0, len - 1);
            return;
        }

        // 从后往前找到比upIndex大的交换位置
        int j = len - 1;
        while (nums[j] <= nums[upIndex]) {
            j--;
        }
        swap(nums, upIndex, j);
        // upIndex后元素反转
        revert(nums, upIndex + 1, len - 1);
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }

    private static void revert(int[] nums,int left,int right) {
        while (left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }

    static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        // i 初始指向第 1 个区间的起始位置
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // j 向后遍历，直到不满足连续递增(即 nums[j] + 1 != nums[j + 1])
            // 或者 j 达到数组边界，则当前连续递增区间 [i, j] 遍历完毕，将其写入结果列表。
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                // 将当前区间 [i, j] 写入结果列表
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                // 将 i 指向更新为 j + 1，作为下一个区间的起始位置
                i = j + 1;
            }
        }
        return res;
    }

    private static int removeTwo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        for (int num : nums) {
            if (start < 2 || num > nums[start - 2]) {
                nums[start++] = num;
            }
        }
        return start;
    }

    static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[start] != nums[i]) {
                nums[start + 1] = nums[i];
                start++;
            }
        }
        return start + 1;
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
        if (startIndex == s.length()) {
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