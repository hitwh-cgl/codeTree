package cn;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 
// 👍 1053 👎 0


public class HouseRobberIi_213 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2};
        solution.rob(nums);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            } else if (nums.length == 2) {
                // why handle two differently, becasue it is difficult to handle boundary;
                return Math.max(nums[0], nums[1]);
            }

            int result;
            int curRob = 0, preRob = nums[0];
            int curNotRob = 0, preNotRob = 0;
            // we assume rob the first house, the last house can not being robbed
            for (int i = 1; i < nums.length - 1; i++) {
                curRob = preNotRob + nums[i];
                curNotRob = Math.max(preRob, preNotRob);
                preRob = curRob;
                preNotRob = curNotRob;
            }
            result = Math.max(curRob, curNotRob);

            preRob = 0;
            preNotRob = 0;
            // we assume not rob the first house;
            for (int i = 1; i < nums.length; i++) {
                curRob = preNotRob + nums[i];
                curNotRob = Math.max(preRob, preNotRob);
                preRob = curRob;
                preNotRob = curNotRob;
            }
            result = Math.max(result, Math.max(curRob, curNotRob));
            return result;
        }


        /**
         * it is more concise;
         */
        public int robV2(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            } else if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            } else {
                return Math.max(
                        subRob(nums, 0, nums.length - 1),
                        subRob(nums, 1, nums.length)
                );
            }
        }

        private int subRob(int[] nums, int start, int end) {
            int rob = nums[start], rob1;
            int notRob = 0, notRob1;
            for (int i = start + 1; i < end; i++) {
                rob1 = notRob + nums[i];
                notRob1 = Math.max(rob, notRob);
                rob = rob1;
                notRob = notRob1;
            }
            return Math.max(rob, notRob);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}