package algorithm;

import java.util.Arrays;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 */
public class FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ints = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, ints, 0, nums1.length);
        System.arraycopy(nums2, 0, ints, nums1.length, nums2.length);
        Arrays.sort(ints);
        if (ints.length % 2 == 0) {
            return (double) (ints[ints.length / 2] + ints[ints.length / 2 - 1]) / 2;
        } else {
            return ints[ints.length / 2];
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2};
        int[] b = {3,4};
        System.out.println(findMedianSortedArrays(a, b));
    }

}
