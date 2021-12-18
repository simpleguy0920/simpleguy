package com.test.java.solution;

import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionNode3 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        LinkedList<Node> list = new LinkedList<>();
        list.offer(node);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Node value = list.pop();
                List<Node> neighbors = value.neighbors;
                if (neighbors != null) {
                    for (Node neighbor : neighbors) {
                        if (!map.containsKey(neighbor)) {
                            Node cloneNeighbor = new Node(neighbor.val, new ArrayList<>());
                            map.put(neighbor, cloneNeighbor);
                            list.offer(neighbor);
                        }
                        map.get(value).neighbors.add(map.get(neighbor));
                    }
                }
            }
        }
        return clone;
    }


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
