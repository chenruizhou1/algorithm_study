package algorithm;

import java.util.HashSet;

/**
 * 给你两个 正整数 数组 arr1 和 arr2 。
 *
 * 正整数的 前缀 是其 最左边 的一位或多位数字组成的整数。例如，123 是整数 12345 的前缀，而 234 不是 。
 *
 * 设若整数 c 是整数 a 和 b 的 公共前缀 ，那么 c 需要同时是 a 和 b 的前缀。例如，5655359 和 56554 有公共前缀 565 和 5655，而 1223 和 43456 没有 公共前缀。
 *
 * 你需要找出属于 arr1 的整数 x 和属于 arr2 的整数 y 组成的所有数对 (x, y) 之中最长的公共前缀的长度。
 *
 * 返回所有数对之中最长公共前缀的长度。如果它们之间不存在公共前缀，则返回 0 。
 */
public class LongestCommonPrefix2 {

    /**
     * 暴力解法，面对数据量大的时候会超时
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int maxLength = 0;

        for (int i : arr1) {
            for (int j : arr2) {
                int a = i;
                int b = j;

                while (a != b) {
                    if (a > b) {
                        a /= 10;
                    } else {
                        b /= 10;
                    }
                }

                if (a == 0) {
                    continue;
                }

                maxLength = Math.max(maxLength, String.valueOf(a).length());
            }
        }

        return maxLength;
    }

    public int longestCommonPrefix2(int[] arr1, int[] arr2) {
        int maxLength = 0;

        HashSet<Integer> set = new HashSet<>();

        for (int i : arr1) {
            while (i > 0) {
                set.add(i);
                i /= 10;
            }
        }

        for (int i : arr2) {
            int cur = i;

            while (cur > 0) {
                if (set.contains(cur)) {
                    maxLength = Math.max(maxLength, String.valueOf(cur).length());
                    break;
                }
                cur /= 10;
            }
        }

        return maxLength;
    }



}
