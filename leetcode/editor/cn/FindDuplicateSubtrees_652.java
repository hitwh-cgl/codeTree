package cn;

//给定一棵二叉树 root，返回所有重复的子树。
//
// 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,null,2,4,null,null,4]
//输出：[[2,4],[4]] 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [2,1,1]
//输出：[[1]] 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [2,2,2,3,null,3,null]
//输出：[[2,3],[3]] 
//
// 
//
// 提示： 
//
// 
// 树中的结点数在[1,10^4]范围内。 
// -200 <= Node.val <= 200 
// 
// Related Topics 树 深度优先搜索 哈希表 二叉树 
// 👍 605 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 1.怎么打印子树；
 * 2.空节点怎么处理
 * 3.节点之间间隔怎么表示
 */
public class FindDuplicateSubtrees_652 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("0,0,0,0,null,null,0,null,null,null,0,null,null");
        Solution solution = new Solution();
        List<TreeNode> result = solution.findDuplicateSubtrees(root);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private HashSet<String> set = new HashSet<>();
        private HashSet<String> resultSet = new HashSet<>();
        private List<TreeNode> result = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            printSubTree(root.left);
            printSubTree(root.right);
            return result;
        }

        private String printSubTree(TreeNode cur) {
            if (cur == null) {
                return "null";
            }
            String tree = cur.val + "-" +
                    printSubTree(cur.left) + "-" +
                    printSubTree(cur.right);
            if (!set.add(tree) && resultSet.add(tree)) {
                resultSet.add(tree);
                result.add(cur);
            }
            return tree;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}