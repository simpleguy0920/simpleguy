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
public class SolutionTree4 {

    static long sum = 0;
    static int result = 0;
    static int max = 0;
    static TreeNode node = null;
    private static String resultStr = null;
    List<List<Integer>> list;

    public static void main(String[] args) {
        SolutionTree4 solutionTree4 = new SolutionTree4();
        TreeNode root = solutionTree4.deserialize("[1,2,null,4,3,4]");
        System.out.println(solutionTree4.delNodes(root, new int[]{2, 3}));
    }

    public String smallestFromLeaf(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        resultStr = null;
        smallestFromLeaf(root, list);
        return resultStr;
    }

    public void smallestFromLeaf(TreeNode root, List<Integer> list) {
        if (root.left == null && root.right == null) {
            StringBuilder builder = new StringBuilder();
            builder.append((char) ('a' + root.val));
            for (int i = list.size() - 1; i >= 0; i--) {
                builder.append((char) ('a' + list.get(i)));
            }
            String str = builder.toString();
            if (resultStr == null) {
                resultStr = str;
            } else if (resultStr.compareTo(str) > 0) {
                resultStr = str;
            }
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            smallestFromLeaf(root.left, list);
        }
        if (root.right != null) {
            smallestFromLeaf(root.right, list);
        }
        list.remove(list.size() - 1);
    }

    public int maxAncestorDiff(TreeNode root) {
        max = 0;
        diffDfs(root);
        return max;
    }

    public void maxAncestorDiffDfs(TreeNode current, TreeNode parent) {
        if (current == null) {
            return;
        }
        int value = Math.abs(current.val - parent.val);
        if (value > max) {
            max = value;
        }
        maxAncestorDiffDfs(current.left, parent);
        maxAncestorDiffDfs(current.right, parent);
    }

    public void diffDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            maxAncestorDiffDfs(root.left, root);
        }
        if (root.right != null) {
            maxAncestorDiffDfs(root.right, root);
        }
    }

    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        StringBuilder builder = new StringBuilder();
        sumRootToLeaf(root, builder);
        return (int) (sum % 1000000007);
    }

    public void sumRootToLeaf(TreeNode root, StringBuilder builder) {
        if (root.left == null && root.right == null) {
            builder.append(root.val);
            String string = builder.toString();
            long value = Long.parseLong(string, 2);
            sum = sum + value;
            builder.deleteCharAt(builder.length() - 1);
            return;
        }
        builder.append(root.val);
        if (root.left != null) {
            sumRootToLeaf(root.left, builder);
        }
        if (root.right != null) {
            sumRootToLeaf(root.right, builder);
        }
        builder.deleteCharAt(builder.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        data = data.substring(1, data.length() - 1);
        String[] array = data.split(",");
        LinkedList<String> source = new LinkedList<>(Arrays.asList(array));
        String rootStr = source.poll();
        TreeNode root = new TreeNode(Integer.parseInt(rootStr));
        list.add(root);
        loop1:
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (treeNode != null) {
                    String leftString = source.poll();
                    if (leftString == null) {
                        break loop1;
                    }
                    String rightString = source.poll();
                    if (rightString == null) {
                        break loop1;
                    }
                    TreeNode left = parse(leftString);
                    TreeNode right = parse(rightString);
                    treeNode.left = left;
                    treeNode.right = right;
                    if (left != null) {
                        list.offer(left);
                    }
                    if (right != null) {
                        list.offer(right);
                    }
                }
            }
        }
        return root;
    }

    public TreeNode parse(String str) {
        if ("null".equals(str)) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(str));
        }
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }
        list.add(root);
        delNodes(root, null, true, set, list);
        return list;
    }

    public void delNodes(TreeNode current, TreeNode parent, boolean left, Set<Integer> set, List<TreeNode> list) {
        if (current == null) {
            return;
        }
        if (set.contains(current.val)) {
            if (parent == null) {
                list.remove(current);
            } else {
                if (left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            if (current.left != null) {
                list.add(current.left);
                delNodes(current.left, null, true, set, list);
            }
            if (current.right != null) {
                list.add(current.right);
                delNodes(current.right, null, false, set, list);
            }
        } else {
            delNodes(current.left, current, true, set, list);
            delNodes(current.right, current, false, set, list);
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int left = lcaDeepestLeaves(root.left, 0);
        int right = lcaDeepestLeaves(root.left, 0);
        if (left == right) {
            return root;
        } else {
            if (left > right) {
                return lcaDeepestLeaves(root.left);
            } else {
                return lcaDeepestLeaves(root.right);
            }
        }

    }

    public int sumEvenGrandparent(TreeNode root) {
        result = 0;
        sumEvenGrandparent(root, null, null);
        return result;

    }

    public void sumEvenGrandparent(TreeNode root, TreeNode parent, TreeNode grandParent) {
        if (root == null) {
            return;
        }
        if (grandParent != null && grandParent.val % 2 == 0) {
            result = result + root.val;
        }
        sumEvenGrandparent(root.left, root, parent);
        sumEvenGrandparent(root.right, root, parent);
    }

    public int deepestLeavesSum(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int sum = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.pop();
                sum = sum + treeNode.val;
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
        }
        return sum;

    }

    public int lcaDeepestLeaves(TreeNode current, int height) {
        if (current == null) {
            return height;
        }
        int leftHeight = lcaDeepestLeaves(current.left, height + 1);
        int rightHeigt = lcaDeepestLeaves(current.right, height + 1);
        return Math.max(leftHeight, rightHeigt);
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        getAllElementsDfs(root1, list1);
        getAllElementsDfs(root2, list2);
        List<Integer> result = new ArrayList<>(list1.size() + list2.size());
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() || index2 < list2.size()) {
            if (index1 == list1.size()) {
                result.add(list2.get(index2));
                index2++;
            } else if (index2 == list2.size()) {
                result.add(list1.get(index1));
                index1++;
            } else {
                int value1 = list1.get(index1);
                int value2 = list2.get(index2);
                if (value1 == value2) {
                    result.add(value1);
                    result.add(value2);
                    index1++;
                    index2++;
                } else if (value1 < value2) {
                    result.add(value1);
                    index1++;
                } else {
                    result.add(value2);
                    index2++;
                }
            }
        }
        return result;
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        node = null;
        getTargetCopyDfs(cloned, target);
        return node;
    }

    public void getTargetCopyDfs(TreeNode root, TreeNode target) {
        if (root == null) {
            return;
        }
        if (node != null) {
            return;
        }
        if (root.val == target.val) {
            node = root;
            return;
        }
        getTargetCopyDfs(root.left, target);
        getTargetCopyDfs(root.right, target);
    }

    public void getAllElementsDfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        getAllElementsDfs(root.left, list);
        list.add(root.val);
        getAllElementsDfs(root.right, list);
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        removeLeafNodes(root, null, true, target);
        return root;
    }

    public void removeLeafNodes(TreeNode current, TreeNode parent, boolean left, int target) {
        if (current == null) {
            return;
        }
        removeLeafNodes(current.left, current, true, target);
        removeLeafNodes(current.right, current, false, target);
        if (current.left == null && current.right == null && parent != null) {
            if (current.val == target) {
                if (left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
    }

    public int pseudoPalindromicPaths(TreeNode root) {
        int[] array = new int[10];
        result = 0;
        pseudoPalindromicPathsDfs(root, array);
        return result;
    }

    public void pseudoPalindromicPathsDfs(TreeNode root, int[] array) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            array[root.val]++;
            if (isPseudoPalindromic(array)) {
                result++;
            }
            array[root.val]--;
        } else {
            array[root.val]++;
            pseudoPalindromicPathsDfs(root.left, array);
            pseudoPalindromicPathsDfs(root.right, array);
            array[root.val]--;
        }

    }

    public boolean isPseudoPalindromic(int[] array) {
        int count = 0;
        for (int value : array) {
            if (value % 2 == 1) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int goodNodes(TreeNode root) {
        result = 1;
        goodNodes(root.left, root.val);
        goodNodes(root.right, root.val);
        return result;
    }

    public void goodNodes(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            result++;
        }
        max = Math.max(max, root.val);
        goodNodes(root.left, max);
        goodNodes(root.right, max);
    }

    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        int currentCount = countPairsCurrent(root, distance);
        int leftCount = countPairs(root.left, distance);
        int rightCount = countPairs(root.right, distance);
        return currentCount + leftCount + rightCount;
    }

    private int countPairsCurrent(TreeNode root, int distance) {
        List<Integer> leftDepth = new ArrayList<>();
        List<Integer> rightDepth = new ArrayList<>();
        countPairs(root.left, leftDepth, 1);
        countPairs(root.right, rightDepth, 1);
        int count = 0;
        for (Integer left : leftDepth) {
            for (Integer right : rightDepth) {
                if (left + right <= distance) {
                    count++;
                }
            }
        }
        return count;
    }

    public void countPairs(TreeNode root, List<Integer> list, int current) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(current);
        } else {
            countPairs(root.left, list, current + 1);
            countPairs(root.right, list, current + 1);
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);

    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new TreeNode(nums[start]);
        } else {
            int mid = (start + end) / 2;
            TreeNode treeNode = new TreeNode(nums[mid]);
            treeNode.left = sortedArrayToBST(nums, start, mid - 1);
            treeNode.right = sortedArrayToBST(nums, mid + 1, end);
            return treeNode;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer max, Integer min) {
        if (root == null) {
            return true;
        }
        if ((max != null && root.val >= max) || (min != null && root.val <= min)) {
            return false;
        }
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalancedCurrent(root) && isBalanced(root.left) && isBalanced(root.right);
    }

    private boolean isBalancedCurrent(TreeNode root) {
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.abs(left - right) <= 1;

    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        inorderSuccessorDfs(root, p, list);
        return list.isEmpty() ? null : list.get(0);

    }

    public void inorderSuccessorDfs(TreeNode root, TreeNode p, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        if (list.size() > 1) {
            return;
        }
        if (root.val < p.val) {
            return;
        }
        inorderSuccessorDfs(root.left, p, list);
        if (root.val > p.val) {
            list.add(root);
        }
        inorderSuccessorDfs(root.right, p, list);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> result1 = new ArrayList<>();
        List<TreeNode> result2 = new ArrayList<>();
        List<TreeNode> list = new ArrayList<>();
        lowestCommonAncestorDfs(root, p, q, list, result1, result2);

        for (int i = 0; i < Math.min(result1.size(), result2.size()); i++) {
            if (result1.get(i) != result2.get(i)) {
                return result1.get(i - 1);
            }
        }
        return null;
    }

    public void lowestCommonAncestorDfs(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> list, List<TreeNode> result1, List<TreeNode> result2) {
        if (root == null) {
            return;
        }
        if (!result1.isEmpty() && !result2.isEmpty()) {
            return;
        }
        list.add(root);
        if (root == p) {
            result1.addAll(list);
        } else if (root == q) {
            result2.addAll(list);
        }
        lowestCommonAncestorDfs(root.left, p, q, list, result1, result2);
        lowestCommonAncestorDfs(root.right, p, q, list, result1, result2);
        list.remove(list.size() - 1);
        return;
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            boolean flag = chechSame(t1, t2);
            if (flag) {
                return true;
            }
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    public boolean chechSame(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 != null && t2 != null) {
            if (t1.val != t2.val) {
                return false;
            } else {
                return chechSame(t1.left, t2.left) && chechSame(t1.right, t2.right);
            }
        } else {
            return false;
        }
    }

    public boolean chechSimilar(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        } else {
            if (t1 == null) {
                return false;
            } else {
                if (t1.val != t2.val) {
                    return false;
                } else {
                    return chechSimilar(t1.left, t2.left) && chechSimilar(t1.right, t2.right);
                }
            }
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            boolean flag = chechSame(A, B);
            if (flag) {
                return true;
            }
        }
        return isSubStructure(A.left, B) || checkSubTree(A.right, B);
    }

    public TreeNode mirrorTree(TreeNode root) {
        mirror(root);
        return root;
    }

    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>(k);
        kthLargest(root, k, list);
        return list.get(k - 1);
    }

    public void kthLargest(TreeNode root, int k, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (list.size() > k) {
            return;
        }
        kthLargest(root.left, k, list);
        list.add(root.val);
        kthLargest(root.right, k, list);
    }

    public TreeNode convertBiNode(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        convertBiNode(root, list);
        TreeNode head = new TreeNode(0);
        TreeNode result = head;
        for (TreeNode treeNode : list) {
            treeNode.left = null;
            treeNode.right = null;
            result.right = treeNode;
            result = result.right;
        }
        return result.right;
    }

    public void convertBiNode(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        convertBiNode(root.left, list);
        list.add(root);
        convertBiNode(root.left, list);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        dfs(root, sum);
        return list;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        pathSum(root, sum, 0, list);
        dfs(root.left, sum);
        dfs(root.right, sum);
    }

    public void pathSum(TreeNode root, int sum, int part, List<Integer> result) {
        result.add(root.val);
        if (part + root.val == sum) {
            list.add(result);
        } else if (part + root.val > sum) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            pathSum(root.left, sum, part + root.val, result);
        }
        if (root.right != null) {
            pathSum(root.right, sum, part + root.val, result);
        }
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n == 1) {
            return 0;
        }
        result = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int m = manager[i];
            List<Integer> list = map.get(m);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i);
                map.put(m, list);
            } else {
                list.add(i);
            }
        }

        numOfMinutes(headID, map, informTime, 0);
        return result;

    }

    public int maxLevelSum(TreeNode root) {
        int max = root.val;
        int maxLength = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int length = 1;
        while (!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                sum = sum + treeNode.val;
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            if (sum > max) {
                max = sum;
                maxLength = length;
            }
            length++;
        }
        return maxLength;
    }

    public void numOfMinutes(int root, Map<Integer, List<Integer>> map, int[] informTime, int time) {
        time = time + informTime[root];
        if (time > result) {
            result = time;
        }
        List<Integer> list = map.get(root);
        if (list == null) {
            return;
        }
        for (Integer integer : list) {
            numOfMinutes(integer, map, informTime, time);
        }
    }

    public TreeNode bstToGst(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        bstToGstDfs(root);
        return root;
    }

    public void bstToGstDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        bstToGstDfs(root.left);
        root.val = root.val + result;
        bstToGstDfs(root.right);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
