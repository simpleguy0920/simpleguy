package com.test.java.sort;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestStack {

    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        testStack.test(Lists.newArrayList(5, 7, 8, 3, 6, 1, 9));
    }

    public void test(List<Integer> list) {
        Stack<Integer> stack = new Stack<>();
        for (Integer integer : list) {
            while (!stack.isEmpty() && stack.peek() < integer) {
                System.out.println(stack.pop());
            }
            stack.push(integer);
        }

        System.out.println("end");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
