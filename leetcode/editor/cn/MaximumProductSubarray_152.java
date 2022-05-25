package cn;

//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 测试用例的答案是一个 32-位 整数。 
//
// 子数组 是数组的连续子序列。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 104 
// -10 <= nums[i] <= 10 
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数 
// 
// Related Topics 数组 动态规划 
// 👍 1667 👎 0


public class MaximumProductSubarray_152 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] max = {2, -5, -2, -4, 3};
        solution.maxProduct(max);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProduct(int[] nums) {
            int max = nums[0];
            int curMax = nums[0], preMax = curMax;
            int curMin = nums[0], preMin = curMin;
            for (int i = 1; i < nums.length; i++) {
                curMax = Math.max(preMax * nums[i], Math.max(preMin * nums[i], nums[i]));
                curMin = Math.min(preMax * nums[i], Math.min(preMin * nums[i], nums[i]));
                max = Math.max(curMax, max);
                preMax = curMax;
                preMin = curMin;
            }
            return max;
        }

        public int dp(int[] nums) {
            int[][] dp = new int[nums.length][2];
            int max = nums[0];
            if (nums[0] > 0) {
                dp[0][0] = nums[0];
            } else if (nums[0] < 0) {
                dp[0][1] = nums[0];
            }
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == 0) {
                    max = Math.max(max, 0);
                } else if (nums[i] > 0) {
                    if (dp[i - 1][0] > 0) {
                        dp[i][0] = dp[i - 1][0] * nums[i];
                    } else {
                        dp[i][0] = nums[i];
                    }
                    max = Math.max(max, dp[i][0]);
                    if (dp[i - 1][1] < 0) {
                        dp[i][1] = dp[i - 1][1] * nums[i];
                    }
                } else {
                    if (dp[i - 1][1] < 0) {
                        dp[i][0] = dp[i - 1][1] * nums[i];
                        max = Math.max(max, dp[i][0]);
                    }

                    if (dp[i - 1][0] > 0) {
                        dp[i][1] = dp[i - 1][0] * nums[i];
                    } else {
                        dp[i][1] = nums[i];
                    }
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}