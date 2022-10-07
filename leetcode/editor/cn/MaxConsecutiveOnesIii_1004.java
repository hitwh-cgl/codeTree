package cn;

//给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// nums[i] 不是 0 就是 1 
// 0 <= k <= nums.length 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 
// 👍 454 👎 0


public class MaxConsecutiveOnesIii_1004 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        Solution solution = new Solution();
        solution.longestOnes(nums, 2);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestOnes(int[] nums, int k) {
            int left = 0, right = 0;
            int count = nums[0] == 0 ? 1 : 0;
            int max = nums[0] == 0 && k < 1 ? 0 : 1;
            while (right < nums.length) {
                right++;
                if (right < nums.length) {
                    count += nums[right] == 0 ? 1 : 0;
                }
                while (count > k && left <= right) {
                    count -= nums[left] == 0 ? 1 : 0;
                    left++;
                }
                if (left <= right && right < nums.length) {
                    max = Math.max(max, right - left + 1);
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}