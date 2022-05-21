package cn;

//给你两棵二叉树： root1 和 root2 。 
//
// 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠
//，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。 
//
// 返回合并后的二叉树。 
//
// 注意: 合并过程必须从两个树的根节点开始。 
//
// 
//
// 示例 1： 
//
// 
//输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//输出：[3,4,5,5,4,null,7]
// 
//
// 示例 2： 
//
// 
//输入：root1 = [1], root2 = [1,2]
//输出：[2,2]
// 
//
// 
//
// 提示： 
//
// 
// 两棵树中的节点数目在范围 [0, 2000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 978 👎 0


public class MergeTwoBinaryTrees_617 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), null),
                new TreeNode(2));
        TreeNode root2 = new TreeNode(2, new TreeNode(1, null, new TreeNode(4)),
                new TreeNode(3, null, new TreeNode(7)));

        Solution solution = new Solution();
        solution.mergeTrees(root1, root2);
        System.out.println(root1.val);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            return mergeNode(root1, root2);
        }

        // [1,3,2,5]
        // [2,1,3,null,4,null,7]
        // 3,4,5,5
        public TreeNode mergeNode(TreeNode target, TreeNode source) {
            if (target == null) {
                return source;
            } else {
                if (source == null) {
                    return target;
                } else {
                    target.val = target.val + source.val;
                    target.left = mergeNode(target.left, source.left);
                    target.right = mergeNode(target.right, source.right);
                    return target;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}