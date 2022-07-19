package algorithm;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 */
public class MagicDictionary {

    private static String[] dictionary;

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        if (dictionary.length > 0) {
            Set<String> set = new HashSet<>(Arrays.asList(dictionary));
            String[] arr = new String[set.size()];
            set.toArray(arr);
            MagicDictionary.dictionary = arr;
        }
    }

    public boolean search(String searchWord) {
        String[] dictionary = MagicDictionary.dictionary;
        if (dictionary == null || dictionary.length == 0) {
            return false;
        }
        if (searchWord == null || searchWord.length() == 0) {
            return false;
        }
        int k;
        for (String s : dictionary) {
            if (searchWord.length() != s.length()) {
                continue;
            }
            k = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != searchWord.charAt(j)) {
                    k++;
                }
                if (k > 1) {
                    break;
                }
            }
            if (k == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] arr = {"MagicDictionary", "buildDict", "search", "search", "search", "search"};
        String searchWord = "leetcode";
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(arr);
        System.out.println(magicDictionary.search(searchWord));
    }

}
