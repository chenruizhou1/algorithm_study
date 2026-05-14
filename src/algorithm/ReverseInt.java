package algorithm;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 */
public class ReverseInt {

    public static int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int digit = x % 10;

            x /= 10;

            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && digit > 7) {
                return 0;
            }

            if (res < Integer.MIN_VALUE / 10 || res == Integer.MIN_VALUE / 10 && digit < -8) {
                return 0;
            }

            res = res * 10 + digit;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }

}
