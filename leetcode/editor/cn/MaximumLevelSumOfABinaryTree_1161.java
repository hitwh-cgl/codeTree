package cn;

//给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
//
// 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,7,0,7,-8,null,null]
//输出：2
//解释：
//第 1 层各元素之和为 1，
//第 2 层各元素之和为 7 + 0 = 7，
//第 3 层各元素之和为 7 + -8 = -1，
//所以我们返回第 2 层的层号，它的层内元素之和最大。
// 
//
// 示例 2： 
//
// 
//输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在 [1, 104]范围内 
// -105 <= Node.val <= 105 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 91 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 优化了一下，9ms到6ms，不知道怎么说好，捂脸
 * @author 17862
 */
public class MaximumLevelSumOfABinaryTree_1161 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1, new TreeNode(7, 7, -8), new TreeNode(0));
        solution.maxLevelSumV2(root);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<Integer> sum = new ArrayList<>();

        public int maxLevelSumV2(TreeNode root) {
            dfs(root, 1);

            int max = Integer.MIN_VALUE;
            int maxLevel = 0;
            for (int i = 0; i < sum.size(); i++) {
                if (sum.get(i) > max) {
                    max = sum.get(i);
                    maxLevel = i + 1;
                }
            }
            return maxLevel;
        }

        private void dfs(TreeNode root, int level) {
            if (root == null) {
                return;
            }

            if (sum.size() < level) {
                sum.add(root.val);
            } else {
                sum.set(level - 1, sum.get(level - 1) + root.val);
            }

            dfs(root.left, level + 1);
            dfs(root.right, level + 1);
        }

        /**
         * 广度优先遍历
         */
        public int maxLevelSum(TreeNode root) {
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offerLast(root);
            int len = 1;
            int max = Integer.MIN_VALUE;
            int maxLevel = 1;
            int curSum = 0;
            int curLevel = 1;
            int curCount = 0;
            for (int i = 0; i < len; i++) {
                TreeNode cur = deque.pollFirst();
                curSum += cur.val;
                if (cur.left != null) {
                    deque.offerLast(cur.left);
                    curCount++;
                }
                if (cur.right != null) {
                    deque.offerLast(cur.right);
                    curCount++;
                }
                if (i + 1 == len) {
                    if (curSum > max) {
                        max = curSum;
                        maxLevel = curLevel;
                    }
                    len += curCount;
                    curSum = 0;
                    curLevel++;
                    curCount = 0;
                }
            }
            return maxLevel;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}