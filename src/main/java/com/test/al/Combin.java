package com.test.al;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class Combin {
    public static void main(String[] args) {
        int[] param = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = new int[9];
        combin(param, result, 0);
        System.out.println("--------------------------");
        List<Integer> pararmList = new ArrayList();
        for (int i : param) {
            pararmList.add(i);
        }
        List<Integer> resultList = new ArrayList<>();
        System.out.println("paramList=" + StringUtils.join(pararmList));
        combin2(pararmList, resultList);
    }

    public static void combin(int[] param, int[] result, int step) {
        if (step == 9) {
            if (result[0] * 100 + result[1] * 10 + result[2] + result[3] * 100 + result[4] * 10 + result[5] == result[6] * 100 + result[7] * 10 + result[8]) {
                System.out.println("" + result[0] + result[1] + result[2] + "+" + result[3] + result[4] + result[5] + "=" + result[6] + result[7] + result[8]);
            }
            return;
        } else {
            for (int i = 0; i < 9; i++) {
                if (param[i] != -1) {
                    result[step] = param[i];
                    param[i] = -1;
                    combin(param, result, step + 1);
                    param[i] = result[step];
                }
            }
        }
    }

    public static void combin2(List<Integer> param, List<Integer> result) {
        if (result.size() == 9) {
            if (result.get(0) * 100 + result.get(1) * 10 + result.get(2) + result.get(3) * 100 + result.get(4) * 10 + result.get(5) == result.get(6) * 100 + result.get(7) * 10 + result.get(8)) {
                System.out.println("" + result.get(0) + result.get(1) + result.get(2) + "+" + result.get(3) + result.get(4) + result.get(5) + "=" + result.get(6) + result.get(7) + result.get(8));
            }
            return;
        } else {
            for (int i = 0; i < param.size(); i++) {
                Integer integer = param.get(i);
                if (integer != -1) {
                    result.add(integer);
                    param.set(i, -1);
                    combin2(param, result);
                    result.remove(integer);
                    param.set(i, integer);
                }
            }
        }
    }
}
