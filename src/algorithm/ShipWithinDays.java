package algorithm;

import java.util.Arrays;

/**
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 */
public class ShipWithinDays {

    public static int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = (left + right) / 2;
            int actDays = 1;
            int sum = 0;
            for (int weight : weights) {
                if (sum + weight > mid) {
                    actDays++;
                    sum = 0;
                }
                sum += weight;
            }
            if (actDays <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{3,3,3,3,3,3};
        int days = 2;
        System.out.println(shipWithinDays(weights, days));
    }

}
