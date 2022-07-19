package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *  判定是否互为字符重排
 *  给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 */
public class CheckPermutation {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bda";
        System.out.println(checkPermutation(s1, s2));
    }

    public static boolean checkPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] chars = str1.toCharArray();
        Map<Character, Integer> map1 = getMap(str1);
        Map<Character, Integer> map2 = getMap(str2);
        for (Character c : chars) {
            if (!map2.containsKey(c) || !map1.get(c).equals(map2.get(c))) {
                return false;
            }
        }
        return true;
    }

    public static Map<Character, Integer> getMap(String str) {
        Map<Character, Integer> map = new HashMap<>(16);
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

}
