package com.test.java.solution;

import java.util.LinkedList;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionNode {

    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            LinkedList<Node> list = new LinkedList<>();
            list.offer(root);
            Node value = root;
            while (!list.isEmpty()) {
                Node node = list.poll();
                Node left = node.left;
                Node right = node.right;
                if (left != null) {
                    list.offer(left);
                }
                if (right != null) {
                    list.offer(right);
                }
                if (value == node) {
                    value = list.peekLast();
                } else {
                    node.next = list.peek();
                }

            }
            return root;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
