package com.test.java.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionNode2 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        LinkedList<Node> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = list.poll();
                row.add(node.val);
                List<Node> children = node.children;
                if (children != null) {
                    for (Node child : children) {
                        list.offer(child);
                    }
                }
            }
            resultList.add(row);
        }
        return resultList;
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        List<Node> children = root.children;
        if (children == null) {
            return 0;
        } else {
            int maxChildren = 0;
            for (Node child : children) {
                int childrenLength = maxDepth(child);
                if (childrenLength > maxChildren) {
                    maxChildren = childrenLength;
                }
            }
            return 1 + maxChildren;
        }

    }


    public int maxDepthWidrh(Node root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        int depth = 0;
        LinkedList<Node> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = list.poll();
                row.add(node.val);
                List<Node> children = node.children;
                if (children != null) {
                    for (Node child : children) {
                        list.offer(child);
                    }
                }
            }
            depth++;
        }
        return depth;
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        List<Node> children = root.children;
        if (children == null) {
            return;
        } else {
            for (Node child : children) {
                dfs(child, list);
            }
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
