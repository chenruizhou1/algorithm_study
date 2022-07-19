package algorithm;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 */
public class MaxArea {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int L = 1; L < height.length; L++) {
            for (int i = 0; i < height.length; i++) {
                int j = i + L;
                if (j >= height.length) {
                    break;
                }
                if (L * Math.min(height[i], height[j]) > maxArea) {
                    maxArea = L * Math.min(height[i], height[j]);
                }
            }
        }
        return maxArea;
    }

    /**
     * 双指针
     */
    public static int maxArea2(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

}
