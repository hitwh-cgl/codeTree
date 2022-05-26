package cn;

//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。如果存在，返回 true ；否则，返回 false 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//解释：等于目标和的根节点到叶节点路径如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
//解释：树中存在两条根节点到叶子节点的路径：
//(1 --> 2): 和为 3
//(1 --> 3): 和为 4
//不存在 sum = 5 的根节点到叶子节点的路径。 
//
// 示例 3： 
//
// 
//输入：root = [], targetSum = 0
//输出：false
//解释：由于树是空的，所以不存在根节点到叶子节点的路径。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 893 👎 0


import java.util.Stack;

public class PathSum_112 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if(root==null){
                return false;
            }

            // 深度优先搜索
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (cur.val == targetSum
                        && cur.right == null && cur.left == null) {
                    return true;
                } else {
                    if (cur.left != null) {
                        cur.left.val = cur.left.val + cur.val;
                        stack.add(cur.left);
                    }
                    if (cur.right != null) {
                        cur.right.val = cur.right.val + cur.val;
                        stack.add(cur.right);
                    }
                }
            }
            return false;
        }


        /**
         * recursion is easy and concise
         */
        public boolean recursion(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            if (root.val == targetSum && root.left == null && root.right == null) {
                return true;
            } else {
                return recursion(root.left, targetSum - root.val)
                        || recursion(root.right, targetSum - root.val);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}