package com.test.java.solution;

import org.apache.commons.lang3.ArrayUtils;

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
public class Subset {

    public static void main(String[] args) {
        String[] num = new String[3];
        for (int i = 0; i < num.length; i++) {
            num[i] = String.valueOf(i);
        }
        Subset subset = new Subset();
        System.out.println(ArrayUtils.toString(num));

        List<List<String>> result = subset.subsets(num);
        System.out.println(ArrayUtils.toString(result));
    }

    public List<List<String>> subsets(String[] nums) {
        List<List<String>> resultList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        backtrace(0, nums, tempList, resultList);
        return resultList;
    }


    public void backtrace(int start, String[] nums, List<String> tempList, List<List<String>> resultList) {
        resultList.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrace(i + 1, nums, tempList, resultList);
            tempList.remove(nums[i]);
        }

    }

}
