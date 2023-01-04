package algorithm;

/**
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 */
public class MaxValue {

    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (verify(index, mid, n, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean verify(int index, int val, int length, int maxSum) {
        int val2 = length - index - 1;
        return val + acc(val, index) + acc(val, val2) <= maxSum;
    }

    public long acc(int val, int length) {
        if (val <= length + 1) {
            return (long) val * (val - 1) / 2 + length - val + 1;
        } else {
            return (2L * val - length - 1) * length / 2;
        }
    }

}
