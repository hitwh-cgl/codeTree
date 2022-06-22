package cn;

//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
//
// 假设二叉树中至少有一个节点。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [2,1,3]
//输出: 1
// 
//
// 示例 2: 
//
// 
//
// 
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1,104] 
// -231 <= Node.val <= 231 - 1 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 306 👎 0


public class FindBottomLeftTreeValue_513 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int maxLength = 0;
        private int target;

        public int findBottomLeftValue(TreeNode root) {
            dfs(root, 1);
            return target;
        }

        private void dfs(TreeNode root, int length) {
            if (root.left == null && root.right == null) {
                if (length > maxLength) {
                    target = root.val;
                    maxLength = length;
                }
                return;
            }

            if (root.left != null) {
                dfs(root.left, length + 1);
            }
            if (root.right != null) {
                dfs(root.right, length + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}