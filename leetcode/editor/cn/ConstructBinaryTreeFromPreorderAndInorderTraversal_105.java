package cn;

//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1602 👎 0


/**
 * 我自己思考的思路里面，难点主要有两个：
 * 一个是如何判断递归函数的退出条件，这个主要考虑边界值；
 * 另一个是如何分割左右子树对应的数组区间，对于中序数组分割比较简单，对于前序数组则依赖中序数组计算出长度后计算偏移值；
 * 值得注意的是，两个数组在递归过程中，长度是相同的，但是坐标不一定相同；
 * @author liuchenguang002
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 3};
        int[] inorder = {2, 3, 1};
        Solution s = new Solution();
        TreeNode node = s.buildTree(preorder, inorder);
        node.val = 1;
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                                   int[] inorder, int inLeft, int inRight) {
            if (inLeft >= preorder.length || inRight < 0 || inLeft > inRight) {
                return null;
            }
            int rootVal = preorder[preLeft];
            for (int i = inLeft; i <= inRight; i++) {
                if (inorder[i] == rootVal) {
                    TreeNode rootNode = new TreeNode(rootVal);
                    // [1,2,3]
                    // [2,3,1]
                    // 1 2 0 1
                    // i=0;
                    rootNode.left = buildTree(preorder, preLeft + 1, preLeft + i - inLeft, inorder, inLeft, i - 1);
                    rootNode.right = buildTree(preorder, preLeft + i - inLeft + 1, preRight, inorder, i + 1, inRight);
                    return rootNode;
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}