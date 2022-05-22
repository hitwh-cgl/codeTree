package cn;

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1756 👎 0


import java.util.ArrayList;
import java.util.List;

/**
 * 1.递归，需要注意元素存储顺序
 * 2.哈希表, 先遍历所有节点，存储child->parent的关系，然后用set存储p节点到root路径，判断q节点到root有无重复节点，第一个重复节点即是目标值；
 * @author liuchenguang002
 */
public class LowestCommonAncestorOfABinaryTree_236 {

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), node4));
        TreeNode root = new TreeNode(3, node5, new TreeNode(1, new TreeNode(0), new TreeNode(8)));

        Solution solution = new Solution();
        solution.lowestCommonAncestor(root, node4, node5);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> left = new ArrayList<>();
            List<TreeNode> right = new ArrayList<>();
            recordRootToChild(root, p, left);
            recordRootToChild(root, q, right);

            TreeNode result = root;
            // left node is at the index of 0;
            for (int m = left.size() - 1, n = right.size() - 1; m >= 0 && n >= 0; ) {
                if (left.get(m).val == right.get(n).val) {
                    result = left.get(m);
                    m--;
                    n--;
                } else {
                    break;
                }
            }
            return result;
        }

        private boolean recordRootToChild(TreeNode node, TreeNode target, List<TreeNode> record) {
            if (node == null) {
                return false;
            }

            if (node.val == target.val) {
                record.add(node);
                return true;
            }

            if (recordRootToChild(node.left, target, record)
                    || recordRootToChild(node.right, target, record)) {
                record.add(node);
                return true;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}