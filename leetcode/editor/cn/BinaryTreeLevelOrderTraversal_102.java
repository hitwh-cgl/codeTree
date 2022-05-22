package cn;

//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 1330 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal_102 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            List<List<Integer>> result = new ArrayList<>();
            Deque<TreeNode> nextDeque = new ArrayDeque<>();
            nextDeque.addFirst(root);
            while (!nextDeque.isEmpty()) {
                Deque<TreeNode> curDeque = nextDeque;
                nextDeque = new ArrayDeque<>();
                ArrayList<Integer> curList = new ArrayList<>(curDeque.size());
                while (!curDeque.isEmpty()) {
                    TreeNode treeNode = curDeque.removeFirst();
                    curList.add(treeNode.val);
                    if (treeNode.left != null) {
                        nextDeque.addLast(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        nextDeque.addLast(treeNode.right);
                    }
                }
                result.add(curList);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}