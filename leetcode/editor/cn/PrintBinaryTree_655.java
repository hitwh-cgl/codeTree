package cn;

//给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩
//阵需要遵循以下规则： 
//
// 
// 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。 
// 矩阵的列数 n 应该等于 2height+1 - 1 。 
// 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。 
// 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 re
//s[r+1][c+2height-r-1] 。 
// 继续这一过程，直到树中的所有节点都妥善放置。 
// 任意空单元格都应该包含空字符串 "" 。 
// 
//
// 返回构造得到的矩阵 res 。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2]
//输出：
//[["","1",""],
// ["2","",""]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3,null,4]
//输出：
//[["","","","1","","",""],
// ["","2","","","","3",""],
// ["","","4","","","",""]]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数在范围 [1, 210] 内 
// -99 <= Node.val <= 99 
// 树的深度在范围 [1, 10] 内 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 187 👎 0


import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree_655 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        Solution solution = new Solution();
        solution.printTree(root);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int height = 0;
        private List<List<String>> result;
        private int n;

        public List<List<String>> printTree(TreeNode root) {
            computeHeight(root, 0);
            result = new ArrayList<>(height + 1);
            n = (int) Math.pow(2, height + 1) - 1;
            for (int i = 0; i < height + 1; i++) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    list.add("");
                }
                result.add(list);
            }
            dfs(root, 0, (n - 1) / 2);
            return result;
        }

        private void dfs(TreeNode cur, int level, int offset) {
            if (cur != null) {
                result.get(level).set(offset, String.valueOf(cur.val));
                dfs(cur.left, level + 1, offset - (int) Math.pow(2, height - level - 1));
                dfs(cur.right, level + 1, offset + (int) Math.pow(2, height - level - 1));
            }
        }

        private void computeHeight(TreeNode root, int curHeight) {
            if (root != null) {
                height = Math.max(height, curHeight);
                computeHeight(root.left, curHeight + 1);
                computeHeight(root.right, curHeight + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}