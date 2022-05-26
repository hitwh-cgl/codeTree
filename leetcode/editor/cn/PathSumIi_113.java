package cn;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// 
// 
// Related Topics 树 深度优先搜索 回溯 二叉树 
// 👍 755 👎 0


import java.util.ArrayList;
import java.util.List;

public class PathSumIi_113 {
    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        private List<List<Integer>> result;

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            result = new ArrayList<>();
            dfs(root, targetSum, new ArrayList<>());
            return result;
        }

        /**
         * 从根节点记录到叶节点，可以有多条路径；
         * 从叶节点记录到根节点，只能有一条路径，不然根节点不好处理；
         */
        private void recordTraceFormRootToLeaf(TreeNode root, int targetSum, List<Integer> preNode) {
            if (root == null) {
                return;
            }

            if (root.val == targetSum && root.left == null && root.right == null) {
                preNode.add(root.val);
                result.add(preNode);
            } else {
                if (root.left != null) {
                    List<Integer> list = new ArrayList<>(preNode);
                    list.add(root.val);
                    recordTraceFormRootToLeaf(root.left, targetSum - root.val, list);
                }
                if (root.right != null) {
                    List<Integer> list = new ArrayList<>(preNode);
                    list.add(root.val);
                    recordTraceFormRootToLeaf(root.right, targetSum - root.val, list);
                }
            }
        }

        /**
         * 优化之处在于减少很多无用数组的创建和填充;
         * 你会看到和上面的思路很香，但是会好很多；
         */
        private void dfs(TreeNode root, int targetSum, List<Integer> preNode) {
            if (root == null) {
                return;
            }

            preNode.add(root.val);
            if (root.val == targetSum && root.left == null && root.right == null) {
                result.add(new ArrayList<>(preNode));
            } else {
                if (root.left != null) {
                    dfs(root.left, targetSum - root.val, preNode);
                }
                if (root.right != null) {
                    dfs(root.right, targetSum - root.val, preNode);
                }
            }
            preNode.remove(preNode.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}