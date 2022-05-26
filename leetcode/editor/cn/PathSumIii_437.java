package cn;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -109 <= Node.val <= 109 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1344 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSumIii_437 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root =
                new TreeNode(10,
                        new TreeNode(5,
                                new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                                new TreeNode(2, null, new TreeNode(1))),
                        new TreeNode(-3, null, new TreeNode(11)));
        int i = solution.pathSum(root, 8);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 使用栈记录当前路径
         */
        public int stack(TreeNode root, int targetSum) {
            dfs(root, targetSum);
            return count;
        }

        private List<Integer> preNode = new ArrayList<>();
        private int count = 0;

        private void dfs(TreeNode root, int target) {
            if (root == null) {
                return;
            }

            if (target == root.val) {
                count++;
            }
            for (int i = preNode.size() - 1, preSum = 0; i >= 0; i--) {
                preSum += preNode.get(i);
                if (preSum == target - root.val) {
                    count++;
                }
            }
            preNode.add(root.val);

            dfs(root.left, target);
            dfs(root.right, target);
            preNode.remove(preNode.size() - 1);
        }


        /**
         * 前缀和优化
         */
        public int pathSum(TreeNode root, int targetSum) {
            prefixCount.put(0, 1);
            recursion(root, targetSum, 0);
            return result;
        }

        Map<Integer, Integer> prefixCount = new HashMap<>();
        private int result = 0;

        public void recursion(TreeNode curNode, int targetSum, int curSum) {
            if (curNode == null) {
                return;
            }

            curSum += curNode.val;
            int curTarget = curSum - targetSum;
            result += prefixCount.getOrDefault(curTarget, 0);
            prefixCount.put(curSum, prefixCount.getOrDefault(curSum, 0) + 1);
            recursion(curNode.left, targetSum, curSum);
            recursion(curNode.right, targetSum, curSum);
            prefixCount.put(curSum, prefixCount.get(curSum) - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}