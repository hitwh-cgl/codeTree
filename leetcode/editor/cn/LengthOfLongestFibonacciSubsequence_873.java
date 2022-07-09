package cn;

//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
//
// 
// n >= 3 
// 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2} 
// 
//
// 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。 
//
// （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 
//是 [3, 4, 5, 6, 7, 8] 的一个子序列） 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入: arr = [1,2,3,4,5,6,7,8]
//输出: 5
//解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
// 
//
// 示例 2： 
//
// 
//输入: arr = [1,3,7,11,12,14,18]
//输出: 3
//解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= arr.length <= 1000 
// 
// 1 <= arr[i] < arr[i + 1] <= 10^9 
// 
// 
// Related Topics 数组 哈希表 动态规划 
// 👍 250 👎 0


import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestFibonacciSubsequence_873 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划
         */
        public int dp(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>(arr.length);
            for (int i = 0; i < arr.length; i++) {
                map.put(arr[i], i);
            }

            int max = 0;
            int[][] dp = new int[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                // arr[j]*2 > arr[i] 减少很多无用查询，提升性能明显 193ms->68ms
                for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                    int before = map.getOrDefault(arr[i] - arr[j], -1);
                    if (before >= 0) {
                        dp[i][j] = Math.max(3, dp[j][before] + 1);
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }

        /**
         * 官方答案是使用动态规划，我自己写的版本更加类似带剪枝的记忆化搜素+深度优先搜索
         */
        public int lenLongestFibSubseq(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>(arr.length);
            for (int i = 0; i < arr.length; i++) {
                map.put(arr[i], i);
            }
            boolean[][] visit = new boolean[arr.length][arr.length];
            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int f0 = i, f1 = j, curLength = 2;
                    if (!visit[f0][f1]) {
                        while (true) {
                            visit[f0][f1] = true;
                            // dfs
                            Integer nextIndex = map.get(arr[f0] + arr[f1]);
                            if (nextIndex != null) {
                                f0 = f1;
                                f1 = nextIndex;
                                curLength++;
                                max = Math.max(max, curLength);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}