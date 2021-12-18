package com;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class WhiteDemo {
    public static void main(String[] args) {
        getWhiteh();
    }

    static void getWhiteh() {
        StringBuilder s0 = new StringBuilder();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();
// 白名单列表
        String whites = "";
        for (String white : whites.split(",")) {

            int abs = (int) Math.abs(Double.parseDouble(white) % 4);
            if (0 == abs) {
                s0.append(white).append(",");
                continue;
            }

            if (1 == abs) {
                s1.append(white).append(",");
                continue;
            }

            if (2 == abs) {
                s2.append(white).append(",");
                continue;
            }
            if (3 == abs) {
                s3.append(white).append(",");
                continue;
            }
        }
        System.out.println("whiteB0:" + s0);
        System.out.println("whiteB1:" + s1);
        System.out.println("whiteB2:" + s2);
        System.out.println("whiteB3:" + s3);
    }
}
