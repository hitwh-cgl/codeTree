package cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liuchenguang002
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 方便书写测试用例
     */
    TreeNode(int val, int left, int right) {
        this.val = val;
        this.left = new TreeNode(left);
        this.right = new TreeNode(right);
    }

    /**
     * 方便输入测试用例
     */
    public static TreeNode deserialize(String data) {
        if (data.startsWith("[")) {
            data = data.substring(1, data.length() - 1);
        }

        if ("".equals(data)) {
            return null;
        }
        String[] value = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(value[0]));
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int index = 1;
        while (!deque.isEmpty() && index < value.length) {
            TreeNode cur = deque.poll();
            String leftStr = value[index++];
            if ("null".equals(leftStr)) {
                cur.left = null;
            } else {
                cur.left = new TreeNode(Integer.parseInt(leftStr));
                deque.add(cur.left);
            }
            String rightStr = value[index++];
            if ("null".endsWith(rightStr)) {
                cur.right = null;
            } else {
                cur.right = new TreeNode(Integer.parseInt(rightStr));
                deque.add(cur.right);
            }
        }

        return root;
    }
}
