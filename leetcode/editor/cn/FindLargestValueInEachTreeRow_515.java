package cn;

//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
//
// 
//
// 示例1： 
//
// 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,104] 
// -231 <= Node.val <= 231 - 1 
// 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 219 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLargestValueInEachTreeRow_515 {
    static //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Deque<TreeNode> queue = new ArrayDeque<>();
            int curLength = 1;
            int nextLength = 0;
            int max = Integer.MIN_VALUE;
            queue.offer(root);
            for (int i = 1; i <= curLength; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) {
                    nextLength++;
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    nextLength++;
                    queue.offer(cur.right);
                }
                if (i == curLength) {
                    curLength += nextLength;
                    nextLength = 0;
                    result.add(max);
                    max = Integer.MIN_VALUE;
                }
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}