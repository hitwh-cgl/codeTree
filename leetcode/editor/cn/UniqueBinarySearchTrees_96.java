package cn;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 
// 👍 1764 👎 0


/**
 * 这种题目还是得
 *
 * @author liuchenguang002
 */
public class UniqueBinarySearchTrees_96 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int numTrees(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            if (n == 3) {
                return 5;
            }

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 5;
            for (int i = 4; i <= n; i++) {
                int cur = 0;
                for (int left = 0; left < i; left++) {
                    cur += dp[left] * dp[i - left - 1];
                }
                dp[i] = cur;
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}