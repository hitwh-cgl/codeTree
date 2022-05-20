package cn;

//给你一个二叉树的根节点 root ， 检查它是否轴对称。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1920 👎 0


public class SymmetricTree_101 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            if (root.left == null && root.right == null) {
                return true;
            }

            if (root.left == null || root.right == null) {
                return false;
            }

            return isMirror(root.left, root.right);
        }

        public boolean isMirror(TreeNode left, TreeNode right) {
            if (left.val != right.val) {
                return false;
            }
            if (left.left == null && right.right != null) {
                return false;
            }
            if (left.left != null && right.right == null) {
                return false;
            }

            if (left.right == null && right.left != null) {
                return false;
            }
            if (left.right != null && right.left == null) {
                return false;
            }

            boolean isMirror = true;
            if (left.left != null && right.right != null) {
                isMirror = isMirror(left.left, right.right);
            }
            if (left.right != null && right.left != null) {
                isMirror &= isMirror(left.right, right.left);
            }
            return isMirror;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}