package cn;

//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。 
//
// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接
//相连的房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 树的节点数在 [1, 104] 范围内 
// 0 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1316 👎 0


public class HouseRobberIii_337 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(TreeNode root) {
            int[] res = recursionRob(root);
            return Math.max(res[0], res[1]);
        }

        /**
         * cur[0] rob current house;
         * cur[1] not rob current house;
         */
        private int[] recursionRob(TreeNode curNode) {
            int[] cur = new int[2];
            cur[0] = curNode.val;
            if (curNode.left != null) {
                int[] left = recursionRob(curNode.left);
                cur[0] += left[1];
                cur[1] += Math.max(left[1], left[0]);
            }
            if (curNode.right != null) {
                int[] right = recursionRob(curNode.right);
                cur[0] += right[1];
                cur[1] += Math.max(right[1], right[0]);
            }
            return cur;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}