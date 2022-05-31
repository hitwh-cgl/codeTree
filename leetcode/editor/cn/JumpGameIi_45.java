package cn;

//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 假设你总是可以到达数组的最后一个位置。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1640 👎 0


public class JumpGameIi_45 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int[] minStep = new int[nums.length];
            minStep[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                minStep[i] = 100000;
            }

            for (int i = 0; i < nums.length; i++) {
                if (i + nums[i] >= nums.length - 1) {
                    return minStep[i] + 1;
                }
                for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                    minStep[i + j] = Math.min(minStep[i + j], minStep[i] + 1);
                }
            }
            return minStep[nums.length - 1];

        }

        public int jump2(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            for (int step = 0, curMax = 0; ; step++) {
                curMax = maxIndex(nums, step, curMax);
                if (curMax >= nums.length - 1) {
                    return step + 1;
                }
            }
        }

        private int maxIndex(int[] nums, int start, int end) {
            int max = start;
            for (int i = start; i <= end; i++) {
                max = Math.max(max, nums[i] + i);
            }
            return max;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
