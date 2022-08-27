package cn;

//给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
//
// 树的 最大宽度 是所有层中最大的 宽度 。 
//
// 
// 
// 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null
// 节点，这些 null 节点也计入长度。 
//
// 题目数据保证答案将会在 32 位 带符号整数范围内。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,3,2,5,3,null,9]
//输出：4
//解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
// 
//
// 示例 2： 
//
// 
//输入：root = [1,3,2,5,null,null,9,6,null,7]
//输出：7
//解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,3,2,5]
//输出：2
//解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [1, 3000] 
// -100 <= Node.val <= 100 
// 
// 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 484 👎 0


import java.util.ArrayList;
import java.util.List;

public class MaximumWidthOfBinaryTree_662 {
    public static void main(String[] args) {
        String source = "[1,1,1,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,null,1,1,null,1,null,1,null,1,null,1,null]";
        Solution solution = new Solution();
        int i = solution.widthOfBinaryTree(TreeNode.deserialize(source));
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // index length value min Index in the level of specify depth;
        private List<Integer> min = new ArrayList<>();
        private int max = 1;

        public int widthOfBinaryTree(TreeNode root) {
            min.add(0);
            dfs(root.left, 1, 0);
            dfs(root.right, 1, 1);
            return max;
        }

        private void dfs(TreeNode cur, int length, int index) {
            if (cur != null) {
                if (min.size() >= length + 1) {
                    max = Math.max(max, index - min.get(length) + 1);
                    // 这么写存在问题，因为按照深度优先遍历顺序，第一个访问的点一定是最左端点，如果校验反而会因为错误的溢出导致左端点设置错误；
//                    if (index < min.get(length)) {
//                        min.set(length, index);
//                    } else {
//                        max = Math.max(max, index - min.get(length) + 1);
//                    }
                } else {
                    min.add(index);
                }
                dfs(cur.left, length + 1, 2 * index);
                dfs(cur.right, length + 1, 2 * index + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}