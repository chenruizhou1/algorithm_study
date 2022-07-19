package algorithm;

import java.util.Arrays;

/**
 * 给你一个数组prices，其中prices[i]是商店里第i件商品的价格。
 * 商店里正在进行促销活动，如果你要买第i件商品，那么你可以得到与 prices[j] 相等的折扣，其中j是满足j > i且prices[j] <= prices[i]的最小下标，如果没有满足条件的j，你将没有任何折扣。
 * 请你返回一个数组，数组中第i个元素是折扣后你购买商品 i最终需要支付的价格。
 */
public class FinalPrices {

    public static void main(String[] args) {
        int[] prices = {8,4,6,2,3};
        System.out.println(Arrays.toString(finalPrices(prices)));
    }

    public static int[] finalPrices(int[] prices) {
        int[] res = new int[prices.length];
        loop:
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    res[i] = prices[i] - prices[j];
                    continue loop;
                }
            }
            res[i] = prices[i];
        }
        res[prices.length - 1] = prices[prices.length - 1];
        return res;
    }

}
