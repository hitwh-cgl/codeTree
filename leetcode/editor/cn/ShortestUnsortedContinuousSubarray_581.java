package cn;

//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
// 
// 
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 
// 👍 854 👎 0


import java.util.Arrays;

public class ShortestUnsortedContinuousSubarray_581 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 2, 3, 4, 5, 2, 6, 3};
        int result = solution.lognV2(nums);
        System.out.println(result);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 先排序，再比较计算出需要排序的位置
         * 1.假设一致是o,不一致是x，可以划分成ABC三个部分，AC完全不变B两侧数字需要重新排序；
         * 注意：数组本身升序情况
         */
        public int findUnsortedSubarray(int[] nums) {
            int[] copy = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copy);
            int left = 0, right = nums.length - 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == copy[i]) {
                    left++;
                } else {
                    break;
                }
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == copy[i]) {
                    right--;
                } else {
                    break;
                }
            }
            return left == nums.length ? 0 : right - left + 1;
        }

        /**
         * 下面这个是我自己想出来的算法，不知道他们的logn解法为什么这么简单？
         * 1.首先从左往右找到违反升序的第一个较小数字，这个数字位置肯定是不对的，需要重新排序，但是如果后面存在比这个更小的数字，那么需要重新排序的位置应该更偏左，所以需要遍历后面找到最小值；
         * 2.为刚才这个最小值找到左侧的合理位置，记录下标left;
         * 3.同理，从右往左找到违反升序的第一个较大数字，再一直往左找到最大值，然后从右侧找到合理位置，记录下标right;
         * 4.如果原数组升序，那么left和right位置便都应该是-1，这种情况需要单独处理；
         */
        public int logn(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }

            // [2,6,4,8,10,9,15]
            // {1, 3, 5, 4, 2}
            int left = -1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    left = i;
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] < nums[left]) {
                            left = j;
                        }
                    }
                    break;
                }
            }
            if (left != -1) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[left] < nums[i]) {
                        left = i;
                        break;
                    }
                }
            }

            int right = -1;
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i - 1] > nums[i]) {
                    right = i - 1;
                    for (int j = i - 1; j >= 0; j--) {
                        if (nums[j] > nums[right]) {
                            right = j;
                        }
                    }
                    break;
                }
            }
            if (right != -1) {
                for (int i = nums.length - 1; i > 0; i--) {
                    if (nums[right] > nums[i]) {
                        right = i;
                        break;
                    }
                }
            }

            return left == -1 ? 0 : right - left + 1;
        }

        /**
         * 下面是目前已知的最优解：
         * 1.从左到右遍历寻找最大值，如果当前值小于最大值，肯定是无序区间，找到有无序子数组的右侧边界；
         * 2.同理从右到左遍历寻找最小值，找到无序子数组的左侧边界；
         */
        public int lognV2(int[] nums) {
            int n = nums.length;
            int left = -1, right = -1;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= max) {
                    max = nums[i];
                } else {
                    right = i;
                }

                if (nums[n - 1 - i] <= min) {
                    min = nums[n - 1 - i];
                } else {
                    left = n - 1 - i;
                }
            }
            return left == -1 ? 0 : right - left + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}