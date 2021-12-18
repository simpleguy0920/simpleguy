package com.test;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.RandomUtils;

public class TestPack {
    public static int[] ZeroOnePack2(int v, int n, int[] weight, int[] value) {
        //动态规划
        int[] dp = new int[v + 1];
        for (int i = 1; i < n + 1; i++) {
            //逆序实现
            for (int j = v; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j - weight[i - 1]] + value[i - 1], dp[j]);
            }
        }
        return dp;
    }

    public static String ZeroOnePack(int V, int N, int[] weight, int[] value) {

        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        //逆推找出装入背包的所有商品的编号
        int j = V;
        String numStr = "";
        for (int i = N; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][j] > dp[i - 1][j]) {
                numStr = i + " " + numStr;
                j = j - weight[i - 1];
            }
            if (j == 0)
                break;
        }
        return numStr;
    }

    public static void main(String[] args) {


        int[] weightArray = getPakage(10);
        int[] priceArray = getPakage(10);
        System.out.println("weight");
        System.out.println(ArrayUtils.toString(weightArray));
        System.out.println("price");
        System.out.println(ArrayUtils.toString(priceArray));
        int[] result = ZeroOnePack2(30, 10, weightArray, priceArray);
        System.out.println(ArrayUtils.toString(result));
        String manyPack = ZeroOnePack(30, 10, weightArray, priceArray);
        System.out.println(manyPack);


    }


    static int[] getPakage(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = RandomUtils.nextInt(20) + 1;
        }
        return array;
    }

}
