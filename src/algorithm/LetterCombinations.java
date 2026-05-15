package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        String[] phoneMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        backtrack(res, phoneMap, digits, new StringBuilder(), 0);

        return res;
    }

    public void backtrack(List<String> res, String[] phoneMap, String digits, StringBuilder sb, int index) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String s = phoneMap[digits.charAt(index) - '0'];

        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            backtrack(res, phoneMap, digits, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinations().letterCombinations("23"));
    }



}
