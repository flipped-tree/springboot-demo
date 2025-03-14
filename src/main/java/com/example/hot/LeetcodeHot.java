package com.example.hot;

import java.util.HashSet;
import java.util.Set;

public class LeetcodeHot {

    public int longest(String input) {
        int result = 0;
        Set<Character> set = new HashSet<>();
        for (int left = 0, right = 0; right < input.length(); right++) {
            while (set.contains(input.charAt(right))) {
                set.remove(input.charAt(left));
                left++;
            }
            set.add(input.charAt(right));
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}