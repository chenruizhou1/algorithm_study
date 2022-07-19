package algorithm;

import java.util.*;

/**
 * 剧情触发时间
 * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0 天），三种属性的值均为 0。
 * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
 * 所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
 * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
 */
public class GetTriggerTime {

    public static void main(String[] args) {
//        int[][] increase = {{2,8,4},{2,5,0},{10,9,8}};
//        int[][] requirements = {{2,11,3},{15,10,7},{9,17,12},{8,1,14}};
        int[][] increase = {{1,1,1}};
        int[][] requirements = {{0,0,0}};
        int[] res = getTriggerTime(increase, requirements);
        System.out.println(Arrays.toString(res));
    }

    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] res = new int[requirements.length];
        for (int i = 1; i < increase.length; i++) {
            increase[i][0] = increase[i][0] + increase[i - 1][0];
            increase[i][1] = increase[i][1] + increase[i - 1][1];
            increase[i][2] = increase[i][2] + increase[i - 1][2];
        }
        for (int i = 0; i < requirements.length; i++) {
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) {
                res[i] = 0;
            } else {
                int left = 0;
                int right = increase.length - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (increase[mid][0] < requirements[i][0] || increase[mid][1] < requirements[i][1] || increase[mid][2] < requirements[i][2]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                if (left < increase.length && increase[left][0] >= requirements[i][0] && increase[left][1] >= requirements[i][1] && increase[left][2] >= requirements[i][2]) {
                    res[i] = left + 1;
                } else {
                    res[i] = -1;
                }
            }
        }
        return res;
    }

}
