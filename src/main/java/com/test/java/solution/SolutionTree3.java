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
public class SolutionTree3 {
    static int count = 0;
    static int diameter = 0;

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        treeNode.left = left;
        left.right = new TreeNode(4);
//        TreeNode right=new TreeNode(3);
//        treeNode.right=right;
//        right.left=new TreeNode(4);
//        right.right=new TreeNode(5);

        SolutionTree3 solutionTree = new SolutionTree3();

        TreeNode source = solutionTree.deserialize("[1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1]");
        System.out.println(solutionTree.findSecondMinimumValue(source));
    }

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

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        count = 0;
        dfs(root, sum);
        return count;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        pathSum(root, sum, 0);
        dfs(root.left, sum);
        dfs(root.right, sum);
    }

    public void pathSum(TreeNode root, int sum, int part) {
        if (part + root.val == sum) {
            count++;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            pathSum(root.left, sum, part + root.val);
        }
        if (root.right != null) {
            pathSum(root.right, sum, part + root.val);
        }
    }

    public int[] findFrequentTreeSum(TreeNode root) {
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
        int sum = findSum(root);
        Integer count = map.get(sum);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        map.put(sum, count);
        if (root.left != null) {
            dfs(root.left, map);
        }
        if (root.right != null) {
            dfs(root.right, map);
        }
    }

    public int findSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + findSum(root.left) + findSum(root.right);
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int value = root.val;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (i == 0) {
                    value = treeNode.val;
                }
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
        }
        return value;
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            int max = list.get(0).val;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (treeNode.val > max) {
                    max = treeNode.val;
                }
                if (i == size - 1) {
                    result.add(max);
                }
                if (treeNode.left != null) {
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.offer(treeNode.right);
                }
            }
        }
        return result;
    }

    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        if (list.size() <= 2) {
            return 0;
        }
        int min = list.get(1) - list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            int value = list.get(i + 1) - list.get(i);
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public void dfs(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        dfs(treeNode.left, list);
        list.add(treeNode.val);
        dfs(treeNode.right, list);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfsTreeNode(root);
        return diameter;
    }

    public void dfsTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        int maxLength = maxLength(root);
        if (maxLength > diameter) {
            diameter = maxLength;
        }
        dfsTreeNode(root.left);
        dfsTreeNode(root.right);
    }

    public int maxLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + maxLengthSingle(root.left) + maxLengthSingle(root.right);
    }

    public int maxLengthSingle(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxLengthSingle(root.left), maxLengthSingle(root.right));
    }

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTarget(root, k, set);
    }

    public boolean findTarget(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        int value = k - root.val;
        if (set.contains(value)) {
            return true;
        } else {
            set.add(root.val);
        }
        return findTarget(root.left, k, set) || findTarget(root.right, k, set);
    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int index = maxNum(nums, 0, nums.length - 1);
        TreeNode treeNode = new TreeNode(nums[index]);
        constructMaximumBinaryTree(treeNode, nums, 0, index - 1, true);
        constructMaximumBinaryTree(treeNode, nums, index + 1, nums.length - 1, false);
        return treeNode;
    }

    public void constructMaximumBinaryTree(TreeNode root, int[] nums, int start, int end, boolean isLeft) {
        int index = maxNum(nums, start, end);
        if (index < 0) {
            return;
        }
        TreeNode treeNode = new TreeNode(nums[index]);
        if (isLeft) {
            root.left = treeNode;
        } else {
            root.right = treeNode;
        }
        constructMaximumBinaryTree(treeNode, nums, start, index - 1, true);
        constructMaximumBinaryTree(treeNode, nums, index + 1, end, false);
    }

    public int maxNum(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }
        int max = nums[start];
        int index = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 1;
        LinkedList<TreeNode> list = new LinkedList<>();
        root.val = 1;
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.poll();
                if (i == 0) {
                    start = treeNode.val;
                }
                if (i == size - 1) {
                    end = treeNode.val;
                }
                if (treeNode.left != null) {
                    treeNode.left.val = 2 * treeNode.val;
                    list.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNode.right.val = 2 * treeNode.val - 1;
                    list.offer(treeNode.right);
                }
            }
            int width = start - end + 1;
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode result = findRoot(root, L, R);
        if (result == null) {
            return result;
        }
        dfsTrimBST(result, L, R);
        return result;
    }

    public void dfsTrimBST(TreeNode root, int L, int R) {
        if (root.left != null) {
            if (root.left.val > R) {
                root.left = root.left.left;
                dfsTrimBST(root, L, R);
            } else if (root.left.val < L) {
                root.left = root.left.right;
                dfsTrimBST(root, L, R);
            } else {
                dfsTrimBST(root.left, L, R);
            }
        }
        if (root.right != null) {
            if (root.right.val > R) {
                root.right = root.right.left;
                dfsTrimBST(root, L, R);
            } else if (root.right.val < L) {
                root.right = root.right.right;
                dfsTrimBST(root, L, R);
            } else {
                dfsTrimBST(root.right, L, R);
            }
        }
    }

    public TreeNode findRoot(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val >= L && root.val <= R) {
            return root;
        }
        if (root.val > R) {
            return findRoot(root.left, L, R);
        } else {
            return findRoot(root.right, L, R);
        }
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        int left;
        int right;
        if (root.left.val != root.val) {
            left = root.left.val;
            right = findSecondMinimumValue(root.right);
        } else if (root.right.val != root.val) {
            left = findSecondMinimumValue(root.left);
            right = root.right.val;
        } else {
            left = findSecondMinimumValue(root.left);
            right = findSecondMinimumValue(root.right);
        }
        if (left == -1 && right == -1) {
            return -1;
        } else if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left == -1) {
            return right;
        } else {
            return left;
        }

    }

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                break;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        dfsInsertIntoBST(root, val);
        return root;
    }

    public void dfsInsertIntoBST(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                dfsInsertIntoBST(root.right, val);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                dfsInsertIntoBST(root.left, val);
            }
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            boolean hasX = false;
            boolean hasY = false;
            TreeNode xParent = null;
            TreeNode yParent = null;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.pop();
                TreeNode left = treeNode.left;
                if (left != null) {
                    list.offer(left);
                    if (left.val == x) {
                        hasX = true;
                        xParent = treeNode;
                    } else if (left.val == y) {
                        hasY = true;
                        yParent = treeNode;
                    }
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    list.offer(right);
                    if (right.val == x) {
                        hasX = true;
                        xParent = treeNode;
                    } else if (right.val == y) {
                        hasY = true;
                        yParent = treeNode;
                    }
                }
                if (hasX && hasY) {
                    break;
                }
            }
            if (hasX && hasY) {
                return xParent != yParent;
            }
        }
        return false;

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
