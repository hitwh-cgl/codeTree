package cn;

//给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。 
//
// 两个节点之间的路径长度 由它们之间的边数表示。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,4,5,1,1,5]
//输出：2
// 
//
// 示例 2: 
//
// 
//
// 
//输入：root = [1,4,5,4,4,5]
//输出：2
// 
//
// 
//
// 提示: 
//
// 
// 树的节点数的范围是 [0, 104]
// -1000 <= Node.val <= 1000 
// 树的深度将不超过 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 567 👎 0


/**
 * 1.以每个节点为中心，向两侧延展
 * 2.自底向上计算局部最优解，判断是否连通
 */
public class LongestUnivaluePath_687 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int longestUnivaluePath(TreeNode root) {
            fromLeafToRoot(root);
            return max;
        }

        /**
         * 与root节点值相同的子节点路径最大长度;
         */
        private int fromLeafToRoot(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = fromLeafToRoot(root.left);
            int rightMax = fromLeafToRoot(root.right);
            int curLeft = 0, curRight = 0;
            if (root.left != null && root.val == root.left.val) {
                curLeft = leftMax + 1;
            }
            if (root.right != null && root.val == root.right.val) {
                curRight = rightMax + 1;
            }
            // 计算最长用的是左右子树路径长度和
            max = Math.max(max, curLeft + curRight);
            // 向上返回用的是左右子树距离最大和
            return Math.max(curLeft, curRight);
        }

        /**
         * 以每个节点为中心，向两侧延展
         */
        public int recursion(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int curMax = expandFromRoot(root);
            max = Math.max(max, curMax);
            recursion(root.left);
            recursion(root.right);
            return max;
        }

        private int max = 0;

        private int expandFromRoot(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int length = 0;
            length += expandWithTarget(root.left, root.val);
            length += expandWithTarget(root.right, root.val);
            return length;
        }

        private int expandWithTarget(TreeNode root, int target) {
            if (root == null || root.val != target) {
                return 0;
            }

            int length = 1;
            int left = expandWithTarget(root.left, target);
            int right = expandWithTarget(root.right, target);
            return length + Math.max(left, right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}