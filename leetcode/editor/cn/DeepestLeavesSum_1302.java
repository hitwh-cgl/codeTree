package cn;

//给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//输出：15
// 
//
// 示例 2： 
//
// 
//输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//输出：19
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 104] 之间。 
// 1 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 100 👎 0


public class DeepestLeavesSum_1302 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int maxDepth = 0;
        private int sum = 0;

        public int deepestLeavesSum(TreeNode root) {
            dfs(root, 1);
            return sum;
        }

        private void dfs(TreeNode cur, int depth) {
            if (cur == null) {
                return;
            }

            if (cur.left == null && cur.right == null) {
                if (depth == maxDepth) {
                    sum += cur.val;
                } else if (depth > maxDepth) {
                    maxDepth = depth;
                    sum = cur.val;
                }
            } else {
                dfs(cur.left, depth + 1);
                dfs(cur.right, depth + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}