package cn;

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
}
