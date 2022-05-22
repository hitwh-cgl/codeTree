package cn;

//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 104] 内 
// -231 <= Node.val <= 231 - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 1591 👎 0


import java.util.ArrayList;

/**
 * 1.第一次做感觉简单得不像一道中等题目，但是发现问题在于只校验当前节点的有效性，没有关注父节点的限制;
 * 例如：5 4 6 null null 3 7； 没有判断那个 3需要大于 5;
 * 而且这个条件是不断变化的，具体的是每个节点如果是爷爷节点的左侧，需要比爷爷小，右侧需要比爷爷大；
 * 这个思路是错的：因为爷爷节点的条件加上以后还会有爷爷爸爸节点的限制，没有办法处理；
 * 2.我想到换一个思路，对于每个节点从上往下搜索，如果能搜索到就说明ok，否者说明有误；
 * <p>
 * 正确思路：
 * 1.带有左右边界值区间的迭代验证；
 * 2.中序遍历为递增序列；
 *
 * @author liuchenguang002
 */
public class ValidateBinarySearchTree_98 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidBST(TreeNode root) {
            // 因为下面的判断涉及int边界值，所以需要升级成Long
            return isValidNode(root, Long.MAX_VALUE, Long.MIN_VALUE);
        }

        private boolean isValidNode(TreeNode node, long max, long min) {
            if (node == null) {
                return true;
            }

            if (node.val >= max || node.val <= min) {
                return false;
            }

            return isValidNode(node.left, node.val, min) &&
                    isValidNode(node.right, max, node.val);
        }

        public boolean order(TreeNode node) {
            ArrayList<Integer> list = new ArrayList<>();
            addElement(node, list);
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) >= list.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }

        public void addElement(TreeNode node, ArrayList<Integer> list) {
            if (node != null) {
                addElement(node.left, list);
                list.add(node.val);
                addElement(node.right, list);
            }
        }

        /**
         * 这是另一个错误思路，想着通过从上往下按照二叉搜索树的查找来解决上层节点的限制问题；
         * 也不行，原因是节点重复的问题；
         */
        public boolean wrongSolution2(TreeNode root) {
            rootNode = root;
            return visitNode(root);
        }

        TreeNode rootNode;

        private boolean visitNode(TreeNode node) {
            if (node == null) {
                return true;
            }
            if (node.left == null && node.right == null) {
                return true;
            }

            boolean valid = true;
            if (node.left != null) {
                valid = node.val > node.left.val;
                if (valid) {
                    valid = visitNode(node.left);
                    if (valid) {
                        valid = canFind(rootNode, node.left.val);
                    }
                }
            }
            if (valid) {
                if (node.right != null) {
                    valid = node.val < node.right.val;
                    if (valid) {
                        valid = visitNode(node.right);
                        if (valid) {
                            valid = canFind(rootNode, node.right.val);
                        }
                    }
                }
            }
            return valid;
        }

        private boolean canFind(TreeNode cur, int val) {
            if (cur == null) {
                return false;
            }
            if (cur.val == val) {
                return true;
            }
            if (cur.val > val) {
                return canFind(cur.left, val);
            } else {
                return canFind(cur.right, val);
            }
        }

        /**
         * 错误思路，往上看是没有出息的；
         * 理解正确答案以后反推，不是往上看不对，而是应该根据上面的节点精确子节点的区间，而不光光是大于或者小于的限制；
         */
        public boolean wrongSolution(TreeNode root) {
            return isValidNode(root, null, null);
        }

        private boolean isValidNode(TreeNode node, Integer max, Integer min) {
            if (node == null) {
                return true;
            }
            if (node.left == null && node.right == null) {
                return true;
            }
            boolean valid = true;
            if (node.left != null) {
                if (max != null) {
                    valid &= node.left.val < max;
                }
                if (min != null) {
                    valid &= node.left.val > min;
                }
                valid &= node.val > node.left.val;
                valid &= isValidNode(node.left, node.val, null);
            }
            if (node.right != null) {
                if (max != null) {
                    valid &= node.right.val < max;
                }
                if (min != null) {
                    valid &= node.right.val > min;
                }
                valid &= node.val < node.right.val;
                valid &= isValidNode(node.right, null, node.val);
            }
            return valid;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}