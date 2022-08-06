package cn;

//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
// 
// 子数组大小 至少为 2 ，且 
// 子数组元素总和为 k 的倍数。 
// 
//
// 如果存在，返回 true ；否则，返回 false 。 
//
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。 
//
// 示例 2： 
//
// 
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
// 
//
// 示例 3： 
//
// 
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 109 
// 0 <= sum(nums[i]) <= 231 - 1 
// 1 <= k <= 231 - 1 
// 
// Related Topics 数组 哈希表 数学 前缀和 
// 👍 453 👎 0


import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum_523 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.checkSubarraySum(new int[]{1, 3, 0, 6}, 6);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            int sum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                Integer self = map.get(sum);
                if (self != null && i - self >= 2) {
                    return true;
                }
                int j = nums[i] + k - (nums[i] % k);
                for (; j <= sum; j += k) {
                    Integer pre = map.get(sum - j);
                    if (pre != null && i - pre >= 2) {
                        return true;
                    }
                }
                map.putIfAbsent(sum, i);
            }
            return false;
        }

        public boolean version2(int[] nums, int k) {
            int sum = 0;
            Map<Integer, Integer> map = new HashMap<>(k);
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int mod = sum % k;
                Integer pre = map.get(mod);
                if (pre != null && i - pre >= 2) {
                    return true;
                }
                map.putIfAbsent(mod, i);
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}