package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 */
public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s, 10));
    }

    public static List<String> findRepeatedDnaSequences(String str, int len) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(16);
        for (int i = 0; i <= str.length() - len; i++) {
            String s = str.substring(i, i + len);
            map.put(s, map.getOrDefault(s, 0) + 1);
            if (2 == map.get(s)) {
                list.add(s);
            }
        }
        return list;
    }

}
