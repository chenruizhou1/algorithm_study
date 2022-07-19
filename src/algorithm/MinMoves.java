package algorithm;

import java.util.Arrays;

/**
 * 最小操作次数使数组元素相等
 * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。
 */
public class MinMoves {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(minMoves(nums));
    }

    /**
     * 动态规划解法
     */
    public static int minMoves(int[] nums) {
        int moves = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            int diff = (moves + nums[i]) - nums[i - 1];
            nums[i] += moves;
            moves += diff;
        }
        return moves;
    }

}
