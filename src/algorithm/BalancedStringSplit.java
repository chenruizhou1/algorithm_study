package algorithm;

/**
 * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
 * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 */
public class BalancedStringSplit {

    public static void main(String[] args) {
        String str = "RLLLLRRRLR";
        System.out.println(balancedStringSplit(str));
    }

    public static int balancedStringSplit(String str) {
        int ans = 0;
        int d = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'L') {
                ++d;
            } else {
                --d;
            }
            if (d == 0) {
                ++ans;
            }
        }
        return ans;
    }

}
