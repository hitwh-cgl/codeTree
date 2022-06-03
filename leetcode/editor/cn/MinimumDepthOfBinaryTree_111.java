package cn;

//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 689 👎 0


import java.util.Stack;

public class MinimumDepthOfBinaryTree_111 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Stack<TreeNode> stack = new Stack<>();
            root.val = 1;
            stack.push(root);
            int min = Integer.MAX_VALUE;
            while (!stack.isEmpty()) {
                root = stack.pop();
                if (root.left == null && root.right == null) {
                    min = Math.min(min, root.val);
                } else {
                    if (root.val >= min) {
                        continue;
                    }

                    if (root.left != null) {
                        root.left.val = root.val + 1;
                        stack.push(root.left);
                    }
                    if (root.right != null) {
                        root.right.val = root.val + 1;
                        stack.push(root.right);
                    }
                }

            }
            return min;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}