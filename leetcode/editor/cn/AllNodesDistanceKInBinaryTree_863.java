package cn;

//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
//
// 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
//输出：[7,4,1]
//解释：所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
// 
//
// 示例 2: 
//
// 
//输入: root = [1], target = 1, k = 3
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// 节点数在 [1, 500] 范围内 
// 0 <= Node.val <= 500 
// Node.val 中所有值 不同 
// 目标结点 target 是树上的结点。 
// 0 <= k <= 1000 
// 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 560 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree_863 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("[0,1,null,null,2,null,3,null,4]");
        Solution solution = new Solution();
        List<Integer> result = solution.distanceK(root, new TreeNode(3), 0);
        System.out.println(result.size());
    }

    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            computeHeight(root, target, 0);
            for (int i = 0; i < path.size() && k - i >= 0; i++) {
                int curK = k - i;
                TreeNode curRoot = path.get(path.size() - 1 - i);
                dfs(curRoot, curK, new ArrayList<>());
            }
            return new ArrayList<>(result);
        }

        private List<TreeNode> path = new ArrayList<>();
        private Set<Integer> visited = new HashSet<>();
        private List<Integer> result = new ArrayList<>();

        private boolean computeHeight(TreeNode cur, TreeNode target, int depth) {
            if (cur == null) {
                return false;
            }
            path.add(cur);
            if (cur.val == target.val) {
                return true;
            } else {
                boolean left = computeHeight(cur.left, target, depth + 1);
                if (left) {
                    return true;
                }
                boolean right = computeHeight(cur.right, target, depth + 1);
                if (right) {
                    return true;
                }
                path.remove(path.size() - 1);
                return false;
            }
        }

        private void dfs(TreeNode cur, int k, List<Integer> list) {
            if (cur == null) {
                return;
            }
            if (!visited.add(cur.val)) {
                return;
            }

            if (list.size() == k) {
                result.add(cur.val);
            }

            list.add(cur.val);
            dfs(cur.left, k, list);
            dfs(cur.right, k, list);
            list.remove(list.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}