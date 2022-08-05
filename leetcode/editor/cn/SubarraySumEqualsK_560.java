package cn;

//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -1000 <= nums[i] <= 1000 
// -107 <= k <= 107 
// 
// Related Topics 数组 哈希表 前缀和 
// 👍 1493 👎 0


import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK_560 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 前缀和+HashMap来优化时间效率： 1806ms->18ms;
         * 1.我们通常的思路是确定左端点，然后遍历右端点，这时后续的计算对之后的遍历常常是越界的，所有没法复用；
         * 如果我们确定当前节点为右端点，遍历左端点，之前的计算结果可以复用，但是仍然需要遍历，时间复杂度一样；
         * 2.这里可以联想到两数之和的思路，将k带入计算， sum[i,j]=k => sum[0,i]-sum[0,j]=k => sum[0,i]-k=sum[o,j],
         * 这样就可以唯一确定目标值，引入哈希表来避免遍历;
         */
        public int subarraySum67mn(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int sum = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                Integer get = map.get(sum - k);
                if (get != null) {
                    count += get;
                }
                Integer pre = map.get(sum);
                if (pre == null) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, pre + 1);
                }
            }
            return count;
        }

        public int subarraySumV1(int[] nums, int k) {
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        result++;
                    }
                }
            }
            return result;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}