package cn;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 
// 👍 2510 👎 0


import java.util.*;

public class LongestIncreasingSubsequence_300 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int length = solution.greedyLengthOfLIS(nums);
        System.out.print(length);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            int[] length = new int[nums.length];
            int max = 1;
            length[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                length[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        length[i] = Math.max(length[i], length[j] + 1);
                    }
                }
                max = Math.max(max, length[i]);
            }
            return max;
        }


        /**
         * 贪心算法
         * 贪心：让每个长度的递增队列最大值最小，这样队列上升最慢；
         */
        public int greedyLengthOfLIS(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            int[] min = new int[nums.length + 1];
            int maxLength = 1;
            min[1] = nums[0];
            for (int index = 1; index < nums.length; index++) {

                int curLength = 1;
                for (int i = 1; i <= maxLength; i++) {
                    if (min[i] < nums[index]) {
                        curLength = Math.max(curLength, i + 1);
                    }
                }
                if (curLength > maxLength) {
                    min[curLength] = nums[index];
                } else {
                    min[curLength] = Math.min(nums[index], min[curLength]);
                }
                maxLength = Math.max(maxLength, curLength);
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}