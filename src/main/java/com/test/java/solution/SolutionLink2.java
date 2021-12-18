package com.test.java.solution;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionLink2 {
    public static void main(String[] args) {
        Date date = new Date(1597717800l * 1000);
        System.out.println(date);
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node start = new Node(0);
        Map<Node, Node> map = new HashMap<>();
        Node result = start;
        while (head != null) {
            Node node = new Node(head.val);
            node.random = head.random;
            map.put(head, node);
            start.next = node;
            start = start.next;
            head = head.next;
        }
        start = result.next;
        while (start != null) {
            start.random = map.get(start.random);
            start = start.next;
        }
        return result.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


}
