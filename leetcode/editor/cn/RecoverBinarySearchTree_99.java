package cn;

//给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,1,4,null,null,2]
//输出：[2,1,4,null,null,3]
//解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。 
//
// 
//
// 提示： 
//
// 
// 树上节点的数目在范围 [2, 1000] 内 
// -231 <= Node.val <= 231 - 1 
// 
//
// 
//
// 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 741 👎 0


public class RecoverBinarySearchTree_99 {
    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        private TreeNode first = null;
        private TreeNode second = null;

        private TreeNode min = null;
        private TreeNode max = null;


        public void recoverTree(TreeNode root) {
            fromMinToMax(root);
            fromMaxToMin(root);
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private void fromMinToMax(TreeNode root) {
            if (root.left != null && first == null) {
                fromMinToMax(root.left);
            }
            if (first == null) {
                if (min == null) {
                    min = root;
                } else {
                    if (root.val > min.val) {
                        min = root;
                    } else {
                        first = min;
                        return;
                    }
                }
            }
            if (root.right != null && first == null) {
                fromMinToMax(root.right);
            }
        }

        private void fromMaxToMin(TreeNode root) {
            if (root.right != null && second == null) {
                fromMaxToMin(root.right);
            }
            if (second == null) {
                if (max == null) {
                    max = root;
                } else {
                    if (root.val < max.val) {
                        max = root;
                    } else {
                        second = max;
                        return;
                    }
                }
            }
            if (root.left != null && second == null) {
                fromMaxToMin(root.left);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}