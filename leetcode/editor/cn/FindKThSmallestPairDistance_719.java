package cn;

//数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
//
// 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
//返回 所有数对距离中 第 k 小的数对距离。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,1], k = 1
//输出：0
//解释：数对和对应的距离如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//距离第 1 小的数对是 (1,1) ，距离为 0 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,6,1], k = 3
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 104 
// 0 <= nums[i] <= 106 
// 1 <= k <= n * (n - 1) / 2 
// 
// Related Topics 数组 双指针 二分查找 排序 
// 👍 384 👎 0


import java.util.Arrays;

/**
 * 好好想一想，为什么双指针比二分法要快；
 *
 * @author 17862
 */
public class FindKThSmallestPairDistance_719 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
            while (left <= right) {
                int mid = (left + right) / 2;
                int cnt = 0;
                for (int i = 0, j = 0; j < n; j++) {
                    while (nums[j] - nums[i] > mid) {
                        i++;
                    }
                    cnt += j - i;
                }
                if (cnt >= k) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public int smallestDistancePairV2(int[] nums, int k) {
            Arrays.sort(nums);
            int min = nums[0];
            int max = nums[nums.length - 1];
            int left = 0, right = max - min;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int count = count(nums, k, mid);
                if (count >= k) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private int count(int[] nums, int k, int distance) {
            int count = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                int end = findCeilingIndex(nums, i, distance);
                count += (end - i - 1);
                if (count > k) {
                    return k + 1;
                }
            }
            return count;
        }

        private int findCeilingIndex(int[] nums, int start, int distance) {
            int left = start, right = nums.length - 1;
            while (left <= right) {
                int mid = (right + left) / 2;
                if (nums[mid] <= nums[start] + distance) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        public int smallestDistancePairV1(int[] nums, int k) {
            int[] count = new int[1000001];
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    count[Math.abs(nums[i] - nums[j])]++;
                }
            }
            for (int i = 0; i < count.length; i++) {
                k -= count[i];
                if (k <= 0) {
                    return i;
                }
            }
            return 0;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}