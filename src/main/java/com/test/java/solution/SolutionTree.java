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
public class SolutionTree {
    static boolean flag = true;
    static int sum = 0;
    static int size = 0;
    static int max = 0;
    Map<Integer, List<TreeNode>> map = new HashMap();

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        treeNode.left = left;
        left.right = new TreeNode(4);
//        TreeNode right=new TreeNode(3);
//        treeNode.right=right;
//        right.left=new TreeNode(4);
//        right.right=new TreeNode(5);

        SolutionTree solutionTree = new SolutionTree();
        TreeNode root = solutionTree.deserialize("[1,2,3,4,5,6]");
        boolean result = solutionTree.isCompleteTree(root);

        System.out.println(result);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        visit(root, list);
        return list;
    }

    public void visit(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        visit(root.left, list);
        list.add(root.val);
        visit(root.right, list);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer minValue, Integer maxValue) {
        if (root == null) {
            return true;
        }
        if (minValue != null && root.val <= minValue) {
            return false;
        }
        if (maxValue != null && root.val >= maxValue) {
            return false;
        }
        return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null) {
            return false;
        } else if (q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null) {
            return false;
        } else if (right == null) {
            return false;
        } else {
            if (left.val != right.val) {
                return false;
            } else {
                return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(root);
        List<Integer> list = new ArrayList<>();
        TreeNode value = root;
        while (!linkedList.isEmpty()) {
            TreeNode treeNode = linkedList.poll();
            list.add(treeNode.val);
            TreeNode left = treeNode.left;
            if (left != null) {
                linkedList.offer(left);
            }
            TreeNode right = treeNode.right;
            if (right != null) {
                linkedList.offer(right);
            }
            if (treeNode == value) {
                lists.add(list);
                list = new ArrayList<>();
                TreeNode last = linkedList.peekLast();
                if (last != null) {
                    value = last;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(root);
        List<Integer> list = new ArrayList<>();
        TreeNode value = root;
        while (!linkedList.isEmpty()) {
            TreeNode treeNode = linkedList.poll();
            list.add(treeNode.val);
            TreeNode left = treeNode.left;
            if (left != null) {
                linkedList.offer(left);
            }
            TreeNode right = treeNode.right;
            if (right != null) {
                linkedList.offer(right);
            }
            if (treeNode == value) {
                lists.add(list);
                list = new ArrayList<>();
                TreeNode last = linkedList.peekLast();
                if (last != null) {
                    value = last;
                }
            }
        }
        for (int i = 0; i < lists.size(); i++) {
            if (i % 2 == 1) {
                List<Integer> list1 = lists.get(i);
                Collections.reverse(list1);
            }
        }
        return lists;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = build(nums, left, mid - 1);
        treeNode.right = build(nums, mid + 1, right);
        return treeNode;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        int length = 1;
        TreeNode last = root;
        linkedList.offer(root);
        while (!linkedList.isEmpty()) {
            TreeNode treeNode = linkedList.poll();
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;
            if (left == null && right == null) {
                break;
            } else if (left != null && right != null) {
                linkedList.offer(left);
                linkedList.offer(right);
            } else {
                if (treeNode == root) {
                    if (left != null) {
                        linkedList.offer(left);
                    }
                    if (right != null) {
                        linkedList.offer(right);
                    }
                } else {
                    break;
                }
            }
            if (treeNode == last) {
                length++;
                last = linkedList.peekLast();
            }
        }
        return length;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return visit(root, root.val, sum);
    }

    public boolean visit(TreeNode root, int current, int value) {
        if (root.left == null && root.right == null) {
            return (current + root.val) == value;
        } else if (root.left != null && root.right != null) {
            return visit(root.left, current + root.val, value) || visit(root.right, current + root.val, value);
        } else if (root.left != null) {
            return visit(root.left, current + root.val, value);
        } else {
            return visit(root.right, current + root.val, value);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return list;
        }
        visit(root, 0, sum, list, result);
        return list;
    }

    public void visit(TreeNode root, int current, int value, List<List<Integer>> list, LinkedList<Integer> result) {
        if (root.left == null && root.right == null) {
            if ((current + root.val) == value) {
                result.addLast(root.val);
                list.add(new ArrayList<>(result));
                result.removeLast();
            } else {
                return;
            }
        } else {
            if (root.left != null) {
                result.addLast(root.val);
                visit(root.left, current + root.val, value, list, result);
                result.removeLast();
            }
            if (root.right != null) {
                result.addLast(root.val);
                visit(root.right, current + root.val, value, list, result);
                result.removeLast();
            }
        }

    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        } else if (root.left == null) {
            flatten(root.right);
        } else {
            if (root.right == null) {
                root.right = root.left;
                root.left = null;
                flatten(root.right);
            } else {
                flatten(root.left);
                flatten(root.right);
                TreeNode right = root.right;
                root.right = root.left;
                TreeNode treeNode;
                for (treeNode = root.right; treeNode.right != null; treeNode = treeNode.right) {

                }
                treeNode.right = right;
                treeNode.left = null;
            }
        }

    }

    public int maxPathSumSingle(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int left = Integer.MIN_VALUE;
        if (root.left != null) {
            left = maxPathSumSingle(root.left);
        }
        int right = Integer.MIN_VALUE;
        if (root.right != null) {
            right = maxPathSumSingle(root.right);
        }
        return root.val + Math.max(0, Math.max(left, right));
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sum = Integer.MIN_VALUE;
        dfsTreeNode(root);
        return sum;
    }

    public void dfsTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        int maxLength = maxSum(root);
        if (maxLength > sum) {
            sum = maxLength;
        }
        dfsTreeNode(root.left);
        dfsTreeNode(root.right);
    }

    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + maxSumSingle(root.left) + maxSumSingle(root.right);
    }

    private int maxSumSingle(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int min = Math.max(maxSumSingle(root.left), maxSumSingle(root.right));
        if (min < 0) {
            return root.val;
        } else {
            return root.val + min;
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sum = 0;
        visit(root, 0);
        return sum;
    }

    public void visit(TreeNode root, int result) {
        if (root.left == null && root.right == null) {
            result = result * 10 + root.val;
            sum = sum + result;
        } else {
            if (root.left != null) {
                visit(root.left, result * 10 + root.val);
            }
            if (root.right != null) {
                visit(root.right, result * 10 + root.val);
            }
        }

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    public void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
                if (i == size - 1) {
                    resultList.add(treeNode.val);
                }
            }
        }
        return resultList;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        count(root);
        return size;
    }

    public void count(TreeNode root) {
        if (root != null) {
            size++;
            count(root.left);
            count(root.right);
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        kthSmallest(root, list, k);
        return list.get(k - 1);
    }

    public void kthSmallest(TreeNode root, List<Integer> list, int k) {
        if (root != null && list.size() <= k) {
            kthSmallest(root.left, list, k);
            list.add(root.val);
            kthSmallest(root.right, list, k);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> list1 = new LinkedList<>();
        findTreeNode(root, p, list1);
        LinkedList<TreeNode> list2 = new LinkedList<>();
        findTreeNode(root, p, list2);
        int position = Math.min(list1.size(), list2.size());
        TreeNode result = null;
        for (int i = 0; i < position; i++) {
            TreeNode treeNode1 = list1.removeFirst();
            TreeNode treeNode2 = list2.removeFirst();
            if (treeNode1 == treeNode2) {
                result = treeNode1;
            }
        }
        return result;
    }

    public boolean findTreeNode(TreeNode root, TreeNode dest, LinkedList<TreeNode> result) {
        if (root == null) {
            return false;
        }
        if (root == dest) {
            return true;
        }
        result.addLast(root);
        boolean flag = findTreeNode(root.left, dest, result) || findTreeNode(root.right, dest, result);
        if (flag) {
            return true;
        } else {
            result.removeLast();
            return false;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if (root == null) {
            return list;
        }
        dfs(root, result, list);
        return list;
    }

    public void dfs(TreeNode root, List<String> result, List<String> list) {
        if (root.left == null && root.right == null) {
            result.add(String.valueOf(root.val));
            list.add(String.join("->", result));
            result.remove(result.size() - 1);
            return;
        }
        if (root.left != null) {
            result.add(String.valueOf(root.val));
            dfs(root.left, result, list);
            result.remove(result.size() - 1);
        }
        if (root.right != null) {
            result.add(String.valueOf(root.val));
            dfs(root.right, result, list);
            result.remove(result.size() - 1);
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (treeNode == null) {
                    builder.append(treeNode).append(",");
                } else {
                    builder.append(treeNode.val).append("|");
                    list.offer(treeNode.left);
                    list.offer(treeNode.right);
                }
            }

        }
        return builder.substring(0, builder.length() - 1) + "]";
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

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root.left, true);
        dfs(root.right, false);
        return sum;
    }

    public void dfs(TreeNode root, boolean isLeft) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (isLeft) {
                sum = sum + root.val;
            }
            return;
        }
        if (root.left != null) {
            dfs(root.left, true);
        }
        if (root.right != null) {
            dfs(root.right, false);
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode node = new TreeNode(-1);
        node.right = root;
        dfs(node.right, key, node, false);
        return node.right;
    }

    public void dfs(TreeNode root, int key, TreeNode parent, boolean isLeft) {
        if (root == null) {
            return;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (root.left == null && root.right != null) {
                if (isLeft) {
                    parent.left = root.right;
                } else {
                    parent.right = root.right;
                }
            } else if (root.left != null && root.right == null) {
                if (isLeft) {
                    parent.left = root.left;
                } else {
                    parent.right = root.left;
                }
            } else {
                if (isLeft) {
                    parent.left = root.left;
                    TreeNode node = parent.left;
                    while (node.right != null) {
                        node = node.right;
                    }
                    node.right = root.right;

                } else {
                    parent.right = root.right;
                    TreeNode node = parent.right;
                    while (node.left != null) {
                        node = node.left;
                    }
                    node.left = root.left;
                }
            }
        } else {
            dfs(root.left, key, root, true);
            dfs(root.right, key, root, false);
        }
    }

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        int maxCount = 0;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                list.clear();
                list.add(entry.getKey());
            } else if (count == maxCount) {
                list.add(entry.getKey());
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public void dfs(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        Integer count = map.get(root.val);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        map.put(root.val, count);
        dfs(root.left, map);
        dfs(root.right, map);
    }

    public TreeNode convertBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfsTree(root, list);
        for (int i = 0; i < list.size(); i++) {
            int sum = 0;
            for (int j = i; j < list.size(); j++) {
                sum = sum + list.get(j).val;
            }
            list.get(i).val = sum;
        }
        return root;
    }

    public void dfsTree(TreeNode treeNode, List<TreeNode> list) {
        if (treeNode == null) {
            return;
        }
        dfsTree(treeNode.left, list);
        list.add(treeNode);
        dfsTree(treeNode.right, list);
    }

    public int findTilt(TreeNode root) {
        dfsFndTilt(root);
        return sum;
    }

    public void dfsFndTilt(TreeNode root) {
        if (root == null) {
            return;
        }
        int tilt = sum(root.right) - sum(root.left);
        tilt = Math.abs(tilt);
        sum = sum + tilt;
        dfsFndTilt(root.left);
        dfsFndTilt(root.right);
    }

    public int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        boolean flag;
        if (s.val == t.val) {
            flag = check(s, t);
            if (flag) {
                return flag;
            }
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean check(TreeNode s, TreeNode t) {
        if (t == null && s == null) {
            return true;
        } else if (s != null && t != null) {
            if (s.val != t.val) {
                return false;
            } else {
                return check(s.left, t.left) && check(s.right, t.right);
            }
        } else {
            return false;
        }
    }

    public String tree2str(TreeNode t) {
        StringBuilder builder = new StringBuilder();
        dfs(t, builder);
        return builder.toString();
    }

    public void dfs(TreeNode treeNode, StringBuilder builder) {
        if (treeNode == null) {
            return;
        }
        builder.append(treeNode.val);
        if (treeNode.right != null) {
            builder.append("(");
            dfs(treeNode.left, builder);
            builder.append(")");
            builder.append("(");
            dfs(treeNode.right, builder);
            builder.append(")");
        } else if (treeNode.left != null) {
            builder.append("(");
            dfs(treeNode.left, builder);
            builder.append(")");
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        dfsMerge(t1, t2);
        return t1;
    }

    public void dfsMerge(TreeNode t1, TreeNode t2) {
        t1.val = t1.val + t2.val;
        if (t1.left != null && t2.left != null) {
            dfsMerge(t1.left, t2.left);
        } else if (t1.left == null && t2.right != null) {
            t1.left = t2.left;
        }
        if (t1.right != null && t2.right != null) {
            dfsMerge(t1.right, t2.right);
        } else if (t1.right == null && t2.right != null) {
            t1.right = t2.right;
        }
    }

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return root;
        }
        if (d == 1) {
            TreeNode treeNode = new TreeNode(v);
            treeNode.left = root;
            return treeNode;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int height = 1;
        while (!list.isEmpty()) {
            int size = list.size();
            if (height == d - 1) {
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = list.poll();
                    TreeNode left = treeNode.left;
                    TreeNode right = treeNode.right;
                    TreeNode beforeLeft = new TreeNode(v);
                    beforeLeft.left = left;
                    treeNode.left = beforeLeft;
                    TreeNode beforeRight = new TreeNode(v);
                    beforeRight.right = right;
                    treeNode.right = beforeRight;
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = list.poll();
                    if (treeNode.left != null) {
                        list.offer(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        list.offer(treeNode.right);
                    }
                }
            }
            height++;
        }
        return root;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> resultList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                sum = sum + treeNode.val;
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
            resultList.add(sum / size);
        }
        return resultList;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();

        dfsSourceTree(root, root, list, set);
        return list;

    }

    public void dfsSourceTree(TreeNode source, TreeNode root, List<TreeNode> treeNodeList, Set<TreeNode> set) {
        dfsDuplicateSubtrees(source, root, treeNodeList, set);
        if (source.left != null) {
            dfsSourceTree(source.left, root, treeNodeList, set);
        }
        if (source.right != null) {
            dfsSourceTree(source.right, root, treeNodeList, set);
        }
    }

    public void dfsDuplicateSubtrees(TreeNode source, TreeNode root, List<TreeNode> treeNodeList, Set<TreeNode> set) {
        if (root == null) {
            return;
        }
        if (isDuplicate(source, root)) {
            if (!set.contains(source)) {
                treeNodeList.add(source);
                set.add(root);
                set.add(source);
            }
        } else {
            dfsDuplicateSubtrees(source, root.left, treeNodeList, set);
            dfsDuplicateSubtrees(source, root.right, treeNodeList, set);
        }
    }

    public boolean isDuplicate(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode1 == null && treeNode2 == null) {
            return true;
        } else if (treeNode1 != null && treeNode2 != null) {
            if (treeNode1 == treeNode2) {
                return false;
            }
            if (treeNode1.val != treeNode2.val) {
                return false;
            } else {
                return isDuplicate(treeNode1.left, treeNode2.left) && isDuplicate(treeNode1.right, treeNode2.right);
            }
        } else {
            return false;
        }
    }

    public int[] levelOrder1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> resultList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                resultList.add(treeNode.val);
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
        }
        int[] array = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            array[i] = resultList.get(i);
        }
        return array;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> resultList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(tree);
        while (!list.isEmpty()) {
            ListNode listNode = new ListNode(0);
            ListNode row = listNode;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                listNode.next = new ListNode(treeNode.val);
                listNode = listNode.next;
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
            resultList.add(row.next);
        }
        return resultList.toArray(new ListNode[0]);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 1) {
            List<TreeNode> list = new ArrayList<>();
            list.add(new TreeNode(1));
            return list;
        } else {
            return getTree(1, n);
        }
    }

    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int[][] array = new int[n][n];

            return getTreeCount(1, n, array);
        }
    }

    public int getTreeCount(int start, int end, int[][] array) {
        if (start >= end) {
            return 1;
        } else {
            if (array[start][end] != 0) {
                return array[start][end];
            } else {
                int sum = 0;
                for (int mid = start; mid <= end; mid++) {
                    sum = sum + getTreeCount(start, mid - 1, array) * getTreeCount(mid + 1, end, array);
                }
                array[start][end] = sum;
                return sum;
            }
        }
    }

    public List<TreeNode> getTree(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        } else if (start == end) {
            list.add(new TreeNode(start));
            return list;
        } else {
            for (int mid = start; mid <= end; mid++) {
                List<TreeNode> leftList = getTree(start, mid - 1);
                List<TreeNode> rightList = getTree(mid + 1, end);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(mid);
                        root.left = left;
                        root.right = right;
                        list.add(root);
                    }
                }
            }
        }
        return list;
    }

    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathDfs(root);
        return max;
    }

    public void longestUnivaluePathDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        int sum = longestUnivaluePath(root.left, root) + longestUnivaluePath(root.right, root);
        if (sum > max) {
            max = sum;
        }
        longestUnivaluePathDfs(root.left);
        longestUnivaluePathDfs(root.right);
    }

    public int longestUnivaluePath(TreeNode root, TreeNode parent) {
        if (root == null) {
            return 0;
        } else if (root.val == parent.val) {
            return 1 + Math.max(longestUnivaluePath(root.left, root), longestUnivaluePath(root.right, root));
        } else {
            return 0;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode node = new TreeNode(1);
        node.right = root;
        pruneTreeDFS(node.right, node, false);
        return node.right;
    }

    public void pruneTreeDFS(TreeNode root, TreeNode parent, boolean isLeft) {

        if (root == null) {
            return;
        }
        System.out.println("root=" + root.val);

        pruneTreeDFS(root.left, root, true);
        pruneTreeDFS(root.right, root, false);

//        if (root.left == null && root.right == null) {
//            System.out.println( "do delete="+root.val);
//            if(root.val!=1){
//                if (isLeft) {
//                    parent.left = null;
//                } else {
//                    parent.right = null;
//                }
//            }
//        }

    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();
        dfsMap(map, root, null);
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(target);
        set.add(target);
        int time = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.pop();
                if (time == K) {
                    result.add(treeNode.val);
                }
                if (treeNode.left != null && !set.contains(treeNode.left)) {
                    list.offer(treeNode.left);
                    set.add(treeNode.left);
                }
                if (treeNode.right != null && !set.contains(treeNode.right)) {
                    list.offer(treeNode.right);
                    set.add(treeNode.right);

                }
                if (map.get(treeNode) != null && !set.contains(map.get(treeNode))) {
                    list.offer(map.get(treeNode));
                    set.add(map.get(treeNode));

                }
            }
            if (time == K) {
                break;
            }
            time++;

        }
        return result;
    }

    public void dfsMap(Map<TreeNode, TreeNode> map, TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        map.put(root, parent);
        dfsMap(map, root.left, root);
        dfsMap(map, root.right, root);
    }

    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        dfs(root, L, R);
        return sum;
    }

    public void dfs(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }
        if (root.val >= L && root.val <= R) {
            sum = sum + root.val;
            dfs(root.left, L, R);
            dfs(root.right, L, R);
        } else if (root.val < L) {
            dfs(root.right, L, R);
        } else {
            dfs(root.left, L, R);
        }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfsLeaf(list1, root1);
        dfsLeaf(list2, root2);
        return list1.equals(list2);

    }

    public List<TreeNode> allPossibleFBT(int N) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        TreeNode treeNode = new TreeNode(0);
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(treeNode);
        map.put(1, treeNodeList);
        treeNodeList = new ArrayList<>();
        treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(0);
        treeNodeList.add(treeNode);
        map.put(3, treeNodeList);
        return allPossibleFBTDFS(N);
    }

    public List<TreeNode> allPossibleFBTDFS(int N) {
        if (map.containsKey(N)) {
            return map.get(N);
        }
        List<TreeNode> treeNodeList = new ArrayList<>();
        if (N % 2 == 1) {
            for (int x = 0; x < N; ++x) {
                int y = N - 1 - x;
                for (TreeNode left : allPossibleFBT(x))
                    for (TreeNode right : allPossibleFBT(y)) {
                        TreeNode bns = new TreeNode(0);
                        bns.left = left;
                        bns.right = right;
                        treeNodeList.add(bns);
                    }
            }
            map.put(N, treeNodeList);

        }
        return treeNodeList;
    }

    public void dfsLeaf(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        dfsLeaf(list, root.left);
        dfsLeaf(list, root.right);
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
    }

    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfsBST(list, root);
        TreeNode x = new TreeNode(0);
        TreeNode result = x;
        for (TreeNode treeNode : list) {
            x.right = treeNode;
            x.left = null;
            x = x.left;
        }
        x.left = null;
        x.right = null;
        return result.right;
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.val != root2.val) {
                return false;
            }
            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
        } else {
            return false;
        }

    }

    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int repectSize = 1;
        boolean lastRow = false;
        boolean hasEmpty = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (repectSize != size) {
                lastRow = true;
            }
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.pop();
                if (treeNode.left != null) {
                    if (lastRow) {
                        return false;
                    }
                    if (hasEmpty) {
                        return false;
                    }
                    queue.offer(treeNode.left);
                } else {
                    hasEmpty = true;
                }
                if (treeNode.right != null) {
                    if (lastRow) {
                        return false;
                    }
                    if (hasEmpty) {
                        return false;
                    }
                    queue.offer(treeNode.right);
                } else {
                    hasEmpty = true;
                }
            }
            repectSize = repectSize * 2;
        }
        return true;

    }


    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root, root.val);

    }

    public boolean isUnivalTree(TreeNode root, int value) {
        if (root == null) {
            return true;
        }
        if (root.val != value) {
            return false;
        }
        return isUnivalTree(root.left, value) && isUnivalTree(root.right, value);
    }

    public void dfsBST(List<TreeNode> list, TreeNode root) {
        if (root == null) {
            return;
        }
        dfsBST(list, root.left);
        list.add(root);
        dfsBST(list, root.right);

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>();
        verticalTraversal(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (Map<Integer, List<Integer>> value : map.values()) {
            List<Integer> row = new ArrayList<>();
            for (List<Integer> linkedList : value.values()) {
                linkedList.sort(Integer::compareTo);
                row.addAll(linkedList);
            }
            list.add(row);
        }
        return list;
    }

    public void verticalTraversal(TreeNode root, int x, int y, TreeMap<Integer, Map<Integer, List<Integer>>> map) {
        if (root == null) {
            return;
        }
        Map<Integer, List<Integer>> column = map.get(x);
        if (column == null) {
            column = new TreeMap<>(Comparator.reverseOrder());
        }
        List<Integer> list = column.get(y);
        if (list == null) {
            list = new ArrayList<>();
            list.add(root.val);
        } else {
            int value = list.get(0);
            list.add(root.val);
        }
        column.put(y, list);
        map.put(x, column);
        verticalTraversal(root.left, x - 1, y - 1, map);
        verticalTraversal(root.right, x + 1, y - 1, map);
    }

    public void distributeCoinsDfs(TreeNode root, List<TreeNode> needList, List<TreeNode> giveList) {
        if (root == null) {
            return;
        }
        if (root.val == 0) {
            needList.add(root);
        } else if (root.val > 1) {
            giveList.add(root);
        }
        distributeCoinsDfs(root.left, needList, giveList);
        distributeCoinsDfs(root.right, needList, giveList);

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
