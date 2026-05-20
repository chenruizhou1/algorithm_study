package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个下标从 0 开始长度为 n 的整数排列 A 和 B 。
 *
 * A 和 B 的 前缀公共数组 定义为数组 C ，其中 C[i] 是数组 A 和 B 到下标为 i 之前公共元素的数目。
 *
 * 请你返回 A 和 B 的 前缀公共数组 。
 *
 * 如果一个长度为 n 的数组包含 1 到 n 的元素恰好一次，我们称这个数组是一个长度为 n 的 排列 。
 */
public class FindThePrefixCommonArray {

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];

        Set<Integer> seen = new HashSet<>();
        int common = 0;

        for (int i = 0; i < n; i++) {
            if (!seen.add(A[i])) {
                common++;
            }

            if (!seen.add(B[i])) {
                common++;
            }

            res[i] = common;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] A = {1,3,2,4};
        int[] B = {3,1,2,4};

        int[] res = findThePrefixCommonArray(A, B);

        System.out.print(Arrays.toString(res));
    }


}
