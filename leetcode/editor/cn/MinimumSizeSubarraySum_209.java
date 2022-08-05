package cn;

//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 109 
// 1 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 
// 👍 1289 👎 0


import java.util.Map;
import java.util.TreeMap;

/**
 * 1.因为是大于等于，所以单纯使用前缀和不能解决问题，需要结合二分查找定位，这里我使用取巧的TreeMap实现二分查找
 * 2.使用滑动窗口复杂度会从O(nlogn)降到O(n)，因为判断起始位置时，不需要二分法而是线性探测；
 */
public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        Solution solution = new Solution();
        int i = solution.slidingWindow(7, nums);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int curSum = 0;
            int minLength = nums.length + 1;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                curSum += nums[i];
                map.put(curSum, i);
                Map.Entry<Integer, Integer> preIndex = map.floorEntry(curSum - target);
                if (preIndex != null) {
                    int length = i - preIndex.getValue();
                    minLength = Math.min(minLength, length);
                }
            }
            return minLength > nums.length ? 0 : minLength;
        }

        public int slidingWindow(int target, int[] nums) {
            int minLength = nums.length + 1;
            int left = 0, right = -1;
            int sum = 0;
            while (++right < nums.length) {
                sum += nums[right];
                if (sum >= target) {
                    while (left < right && sum - nums[left] >= target) {
                        sum -= nums[left++];
                    }
                    minLength = Math.min(minLength, right - left + 1);
                }
            }
            return minLength > nums.length ? 0 : minLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}