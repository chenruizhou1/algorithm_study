package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 */
public class AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        for (int i = 0; i < asteroids.length - 1; i++) {
            if (asteroids[i] > 0 && asteroids[i + 1] < 0) {
                if (asteroids[i] > -asteroids[i + 1]) {
                    asteroids[i + 1] = 0;
                } else if (asteroids[i] < -asteroids[i + 1]) {
                    asteroids[i] = 0;
                } else {
                    asteroids[i] = 0;
                    asteroids[i + 1] = 0;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) {
            if (a != 0) {
                list.add(a);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        if (res.length != asteroids.length) {
            res = asteroidCollision(res);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a = {-2, -1, 1, 2};
        System.out.println(Arrays.toString(asteroidCollision(a)));
    }


}
