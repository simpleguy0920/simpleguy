package com.test.java.solution;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionNodeTree {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                for (int k = j + 1; k <= 9; k++) {
                    for (int l = k + 1; l <= 9; l++) {
                        System.out.println(i * 1000 + j * 100 + k * 10 + l);
                        sum++;
                    }
                }
            }
        }
        System.out.println("sum=" + sum);
        sum = 0;

        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                sum++;
            }
        }
        System.out.println("sum=" + sum);
        sum = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                for (int k = j + 1; k <= 9; k++) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);

    }


}
