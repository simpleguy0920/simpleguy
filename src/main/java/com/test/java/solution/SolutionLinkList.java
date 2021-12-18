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
public class SolutionLinkList {
    ListNode successor = null;

    public static void main(String[] args) {
        SolutionLinkList solutionLinkList = new SolutionLinkList();
        ListNode listNode = convertArrayToListNode(new int[]{4, 5, 1, 9});
        solutionLinkList.deleteNode(listNode);
        System.out.println(convertLinkToList(listNode));
//        System.out.println(Arrays.toString(solutionLinkList.nextLargerNodes(listNode)));
    }

    public static ListNode convertArrayToListNode(int[] array) {
        ListNode node = new ListNode(array[0]);
        ListNode result = node;
        for (int j = 1; j < array.length; j++) {
            result.next = new ListNode(array[j]);
            result = result.next;
        }
        return node;
    }

    public static List<String> convertLinkToList(ListNode listNode) {
        List<String> list = new ArrayList<>();
        while (listNode != null) {
            list.add(String.valueOf(listNode.val));
            listNode = listNode.next;
        }
        return list;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0;
        int value = l1.val + l2.val;
        if (value > 9) {
            value = value - 10;
            flag = 1;
        }
        ListNode listNode = new ListNode(value);
        ListNode result = listNode;

        while ((l1 != null && l1.next != null) || (l2 != null && l2.next != null)) {
            int v1 = 0;
            int v2 = 0;
            if (l1 != null && l1.next != null) {
                l1 = l1.next;
                v1 = l1.val;
            }
            if (l2 != null && l2.next != null) {
                l2 = l2.next;
                v2 = l2.val;
            }
            value = v1 + v2 + flag;
            if (value >= 10) {
                value = value - 10;
                flag = 1;
            } else {
                flag = 0;
            }
            listNode.next = new ListNode(value);
            listNode = listNode.next;
        }
        if (flag > 0) {
            listNode.next = new ListNode(flag);
        }
        return result;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int length = list.size();
        int start = length - n - 1;
        if (n <= 1) {
            if (length == 1) {
                return head;
            } else {
                list.get(start).next = null;
            }
        } else {
            if (n == length) {
                return list.get(1);
            } else {
                list.get(start).next = list.get(start + 2);
            }
        }
        return list.get(0);

    }

    public static ListNode swapPairs(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode node = result;

        while (head != null && head.next != null) {
            result.next = new ListNode(head.next.val);
            result.next.next = new ListNode(head.val);
            head = head.next.next;
            result = result.next.next;
        }
        return node.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int currentValue = 0;
        if (l1.val <= l2.val) {
            currentValue = l1.val;
            l1 = l1.next;
        } else {
            currentValue = l2.val;
            l2 = l2.next;
        }
        ListNode result = new ListNode(currentValue);
        ListNode node = result;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    currentValue = l1.val;
                    l1 = l1.next;
                } else {
                    currentValue = l2.val;
                    l2 = l2.next;
                }

            } else if (l1 != null) {
                currentValue = l1.val;
                l1 = l1.next;
            } else if (l2 != null) {
                currentValue = l2.val;
                l2 = l2.next;
            }
            node.next = new ListNode(currentValue);
            node = node.next;
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode listNode;
        if (l1.val < l2.val) {
            listNode = new ListNode(l1.val);
            listNode.next = mergeTwoLists(l1.next, l2);
        } else {
            listNode = new ListNode(l2.val);
            listNode.next = mergeTwoLists(l1, l2.next);
        }
        return listNode;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        ListNode listNode1 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode listNode = lists[i];
            listNode1 = mergeTwoLists(listNode1, listNode);
        }
        return listNode1;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = getLength(head);
        for (int i = 0; i < k % length; i++) {
            head = rotateRightOne(head);
        }
        return head;
    }

    public ListNode rotateRightOne(ListNode head) {
        ListNode listNode = head;
        while (listNode.next.next != null) {
            listNode = listNode.next;
        }
        ListNode start = listNode.next;
        start.next = head;
        listNode.next = null;
        return start;
    }

    public int getLength(ListNode head) {
        ListNode listNode = head;
        int i = 0;
        while (listNode.next != null) {
            listNode = listNode.next;
            i++;
        }
        return i + 1;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;  // 若head为空则直接返回null
        ListNode dummy = new ListNode(-1);  // 建立一个虚拟头结点
        ListNode tail = dummy;  // 定义一个尾巴，用于尾插法。
        for (ListNode l = head, r = head; l != null; l = r) {
            while (r != null && r.val == l.val) r = r.next;  // 只要r不为空并且与l的值相等则一直向后移动
            if (l.next == r) {  // 若长度为1，则通过尾插法加入。
                tail.next = l;  // 基本的尾插法
                tail = l;
                tail.next = null;  // 这里记得将尾部的后面置为null，不然可能后面会带着一些其他的节点。
            }
        }
        return dummy.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0);
        ListNode lessNode = less;
        ListNode bigger = new ListNode(0);
        ListNode biggerNode = bigger;
        while (head != null) {
            if (head.val < x) {
                less.next = new ListNode(head.val);
                less = less.next;
            } else {
                bigger.next = new ListNode(head.val);
                bigger = bigger.next;
            }
            head = head.next;
        }
        less.next = biggerNode.next;
        return lessNode.next;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        boolean flag = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                flag = true;
                fast = head;
            }
        }
        if (!flag) {
            return null;
        }
        while (true) {
            fast = fast.next;
            slow = slow.next;
            if (fast == slow) {
                return fast;
            }
        }


    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = 0;
        int j = list.size() - 1;
        ListNode start = new ListNode(0);
        head = start;
        while (i <= j) {
            if (i <= j) {
                start.next = list.get(i);
                start = start.next;
                i++;
            }
            if (i <= j) {
                start.next = list.get(j);
                start = start.next;
                j--;
            }
        }
        start.next = null;
        head = head.next;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headA = headA.next;
        }
        return null;

    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
            return head;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        } else if (head.next == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
//    public ListNode oddEvenList(ListNode head) {
//        if(head==null|| head.next==null){
//            return head;
//        }
//        ListNode first=head;
//        ListNode second=head.next;
//        int i=0;
//        while(head!=null){
//            if(i>)
//
//        }
//
//
//
//
//    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode listNode1 = new ListNode(0);
        ListNode start1 = listNode1;
        ListNode listNode2 = new ListNode(0);
        ListNode start2 = listNode2;
        for (int i = 1; head.next != null; i++) {
            head = head.next;
            if (i % 2 == 1) {
                listNode1.next = head;
                listNode1 = listNode1.next;
            } else {
                listNode2.next = head;
                listNode2 = listNode2.next;
            }
        }
        listNode1.next = start2.next;
        return start1.next;
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null & fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }

    public int[] nextLargerNodes(ListNode head) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int num = 0;
        ListNode start = head;
        while (head != null) {
            num++;
            Integer count = map.get(head.val);
            if (count == null) {
                map.put(head.val, 1);
            } else {
                map.put(head.val, map.get(head.val) + 1);
            }
            head = head.next;
        }
        int[] array = new int[num];
        num = 0;
        while (start != null) {
            int count = map.get(start.val);
            if (count == 1) {
                map.remove(start.val);
            } else {
                map.put(start.val, map.get(start.val) - 1);
            }
            if (map.isEmpty()) {
                array[num] = 0;
            } else {
                int max = map.lastKey();
                if (max <= start.val) {
                    array[num] = 0;

                } else {
                    array[num] = max;

                }
            }

            start = start.next;
            num++;
        }
        return array;
    }

    public int getDecimalValue(ListNode head) {
        int sum = 0;
        while (head != null) {
            sum = sum * 2 + head.val;
            head = head.next;
        }
        return sum;

    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.val == head.val) {
            boolean flag = isSame(head, root);
            if (flag) {
                return true;
            }
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean isSame(ListNode head, TreeNode root) {
        if (head == null && root == null) {
            return true;
        } else if (head != null && root != null) {
            if (head.val != root.val) {
                return false;
            } else {
                return isSame(head.next, root.left) || isSame(head.next, root.right);
            }
        } else {
            return false;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        ListNode parent = head;
        ListNode child = head.next;
        set.add(parent.val);
        while (child != null) {
            if (set.contains(child.val)) {
                parent.next = child.next;
                child = child.next;
            } else {
                set.add(child.val);
                parent = parent.next;
                child = child.next;
            }
        }
        return head;

    }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] array = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            array[size - 1 - i] = list.get(i);
        }
        return array;

    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;


    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode start = head;
        ListNode second = head.next;
        ListNode next = reverseList(head.next);
        second.next = start;
        start.next = null;
        return next;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode start = head;
        if (head.val == val) {
            return head.next;
        }
        ListNode parent = head;
        ListNode child = head.next;
        while (child != null) {
            if (child.val == val) {
                parent.next = child.next;
                child = child.next;
            } else {
                parent = parent.next;
                child = child.next;

            }
        }
        return start;
    }

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return sortedListToBST(list, 0, list.size());
    }

    public TreeNode sortedListToBST(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode treeNode = new TreeNode(list.get(mid));
        treeNode.left = sortedListToBST(list, start, mid - 1);
        treeNode.right = sortedListToBST(list, mid + 1, end);
        return treeNode;
    }

    public void deleteNode(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        int i = 0;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            if (i > 0) {
                slow = slow.next;
            }
            i++;
        }
        slow.next = slow.next.next;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}