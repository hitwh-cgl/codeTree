package cn;

//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1448 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 递归思路
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            walk(res, root);
            return res;
        }

        private void walk(List<Integer> res, TreeNode root) {
            if (root != null) {
                walk(res, root.left);
                res.add(root.val);
                walk(res, root.right);
            }
        }

        /**
         * 迭代思路
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}