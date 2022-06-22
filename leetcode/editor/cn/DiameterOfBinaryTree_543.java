package cn;

//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//          1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1041 👎 0


public class DiameterOfBinaryTree_543 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 二叉树的直径可以转换为从该节点出发的左右子树最大深度之和+1,假设根节点深度为0;
         * 因为不一定经过根节点，所以最大直径的路径根节点可能是某个子树；
         */
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return 0;
            }
            computeMax(root);
            return max;
        }

        int max = 1;

        /**
         * 我们需要求每个节点左右子树的最大深度，然后得到该节点的深度作为父节点的深度；
         * 在这个遍历过程中我们需要不断更新最大左右子树路径长度；
         */
        private int computeMax(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int leftDepth = computeMax(node.left);
            int rightDepth = computeMax(node.right);
            max = Math.max(max, leftDepth + rightDepth);
            return Math.max(leftDepth, rightDepth) + 1;
        }
        //       (3)1
        //         / \
        //     (2)2   3(1)
        //       / \
        //   (1)4   5(1)
    }
//leetcode submit region end(Prohibit modification and deletion)

}