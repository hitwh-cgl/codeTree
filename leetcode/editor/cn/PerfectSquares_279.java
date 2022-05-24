package cn;

//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 1375 👎 0


import java.util.ArrayList;

public class PerfectSquares_279 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numSquares(int n) {
            return 0;
        }

        /**
         * 如果你将从从1到n之间的所有完全平方数看作硬币，其实这题类似零钱兑换；
         * 我纠结的点是n的取值范围最大可以是10的四次方，太大了，我担心内存会不够，但是其实是可以这么做的；
         */
        public int dp(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }

            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = n + 1;
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
                }
            }
            return dp[n];
        }


        /**
         * 我一开始理解贪心是错误的，比如18一开始设想他是16，然后再拆解2，发现没有9+9来的小，这让我觉得贪心思路有问题；
         * 正确的贪心思路，尝试使用一个数来表示，不行就两个，再不行就三个，逐个放宽条件；
         */
        public int greed(int n) {
            for (int i = 1; i < n; i++) {
                if (useK(n, i)) {
                    return i;
                }
            }
            return n;
        }

        public boolean useK(int n, int k) {
            if (k == 0) {
                return false;
            }

            for (int i = 1; i < n; i++) {
                int product = i * i;
                if (product == n) {
                    return true;
                } else if (product < n) {
                    if (useK(n - product, k - 1)) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}