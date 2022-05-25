package cn;

//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 
// 👍 1217 👎 0


public class TargetSum_494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        Solution solution = new Solution();
        solution.dp(nums, 3);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int result = 0;
        int totalAbsSum = 0;

        public int findTargetSumWays(int[] nums, int target) {
            for (int num : nums) {
                totalAbsSum += num;
            }
            findWay(nums, 0, 0, 0, target);
            return result;
        }

        private void findWay(int[] nums, int curIndex, int curSum, int curAbs, int target) {
            if (curSum + totalAbsSum - curAbs < target) {
                return;
            }
            if (curSum - (totalAbsSum - curAbs) > target) {
                return;
            }

            if (curIndex == nums.length - 1) {
                if (curSum + nums[curIndex] == target) {
                    result++;
                }
                if (curSum - nums[curIndex] == target) {
                    result++;
                }
            } else {
                curAbs += nums[curIndex];
                findWay(nums, curIndex + 1, curSum + nums[curIndex], curAbs, target);
                findWay(nums, curIndex + 1, curSum - nums[curIndex], curAbs, target);
            }
        }

        /**
         * 绝对值之和是totalAbsSum,目标值是target,那么负数的绝对值之和是：negative;
         * totalAbsSum-negative-negative=target => (totalAbsSum -target )/2=negative
         * the question change to how to select some nums of the array which sum negative;
         */
        public int dp(int[] nums, int target) {
            for (int num : nums) {
                totalAbsSum += num;
            }
            int difference = totalAbsSum - target;
            if (difference < 0 || difference % 2 != 0) {
                return 0;
            }
            int negative = difference / 2;
            // dp[x] how many way to get x; 接着就是背包问题
            int[] dp = new int[negative + 1];
            dp[0] = 1;
            for (int num : nums) {
                for (int j = negative; j >= num; j--) {
                    dp[j] += dp[j - num];
                }
            }
            return dp[negative];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}