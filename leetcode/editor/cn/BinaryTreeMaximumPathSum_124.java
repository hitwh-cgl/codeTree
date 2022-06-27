package cn;

//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1636 👎 0


public class BinaryTreeMaximumPathSum_124 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxPathSum(new TreeNode(1, 2, 3));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int maxPathSum(TreeNode root) {
            maxSubPath(root);
            return max;
        }

        private int max = -1000;

        private int maxSubPath(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = root.val;
            int leftMax = maxSubPath(root.left);
            if (leftMax > 0) {
                sum += leftMax;
            }
            int rightMax = maxSubPath(root.right);
            if (rightMax > 0) {
                sum += rightMax;
            }
            max = Math.max(max, sum);
            return Math.max(0, Math.max(root.val, Math.max(root.val + leftMax, root.val + rightMax)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}