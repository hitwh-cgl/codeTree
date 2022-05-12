package cn;

//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
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
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 
// 👍 1167 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FlattenBinaryTreeToLinkedList_114 {
//leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            List<TreeNode> treeNodeList = new ArrayList<>();
            preOrderTravel(root, treeNodeList);

            for (int i = 0; i < treeNodeList.size() - 1; i++) {
                TreeNode treeNode = treeNodeList.get(i);
                TreeNode treeNode2 = treeNodeList.get(i + 1);
                treeNode.left = null;
                treeNode.right = treeNode2;
            }
        }


        private void preOrderTravel(TreeNode root, List<TreeNode> treeNodeList) {
            if (root != null) {
                treeNodeList.add(root);
                preOrderTravel(root.left, treeNodeList);
                preOrderTravel(root.right, treeNodeList);
            }
        }


        /**
         * 前序遍历时展开
         */
        private void function(TreeNode root) {
            if (root == null) {
                return;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode prev = null;
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (prev != null) {
                    prev.left = null;
                    prev.right = cur;
                }
                TreeNode left = cur.left, right = cur.right;
                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
                prev = cur;
            }
        }

        /**
         * 前序遍历时展开
         */
        private TreeNode function2(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode left = root.left, right = root.right;
            if (left != null) {
                root.right = left;
                root.left = null;
                root = function2(left);
            }
            if (right != null) {
                root.right = right;
                root = function2(right);
            }
            return root;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}