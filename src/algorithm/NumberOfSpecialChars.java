package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 word。如果 word 中同时存在某个字母的小写形式和大写形式，则称这个字母为 特殊字母 。
 * 返回 word 中 特殊字母 的数量。
 */
public class NumberOfSpecialChars {

    public int numSpecialChars(String word) {
        int res = 0;

        Set<Character> lower = new HashSet<>();
        Set<Character> upper = new HashSet<>();

        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower.add(c);
            }
            if (Character.isUpperCase(c)) {
                upper.add(c);
            }
        }

        for (char c : lower) {
            if (upper.contains(Character.toUpperCase(c))) {
                res++;
            }
        }

        return res;
    }


}
