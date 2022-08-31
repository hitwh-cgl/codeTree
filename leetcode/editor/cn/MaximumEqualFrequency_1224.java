package cn;

//给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
//
// 
// 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。 
// 
//
// 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,1,1,5,3,3,5]
//输出：7
//解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数
//字都出现了两次。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
//输出：13
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 
// Related Topics 数组 哈希表 
// 👍 168 👎 0


import java.util.HashMap;
import java.util.Map;

public class MaximumEqualFrequency_1224 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5};
        Solution solution = new Solution();
        int i = solution.maxEqualFreqWithArray(nums);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxEqualFreq(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            Map<Integer, Integer> freq = new HashMap<>();
            int maxLength = 1, maxFreq = 1;
            for (int i = 0; i < nums.length; i++) {
                Integer times = count.get(nums[i]);
                if (times == null) {
                    freq.put(1, freq.getOrDefault(1, 0) + 1);
                    count.put(nums[i], 1);
                } else {
                    freq.put(times, freq.get(times) - 1);
                    freq.put(times + 1, freq.getOrDefault(times + 1, 0) + 1);
                    count.put(nums[i], times + 1);
                    maxFreq = Math.max(maxFreq, times + 1);
                }

                if (maxFreq == 1
                        || (freq.get(1) == 1 && freq.get(maxFreq) * maxFreq == i)
                        || (freq.get(maxFreq) == 1 && freq.get(maxFreq - 1) * (maxFreq - 1) + maxFreq - 1 == i)
                ) {
                    maxLength = i + 1;
                }
            }
            return maxLength;
        }

        /**
         * 优化思路，数据范围小时，使用数组来做哈希表
         */
        public int maxEqualFreqWithArray(int[] nums) {
            int[] count = new int[100001];
            int[] freq = new int[100001];
            int maxFreq = 1;
            int maxLength = 1;
            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];
                int times = count[cur];
                if (freq[times] > 0) {
                    freq[times]--;
                }
                freq[times + 1]++;
                count[cur]++;
                maxFreq = Math.max(maxFreq, count[cur]);
                if (maxFreq == 1
                        || freq[maxFreq] * maxFreq + 1 == i + 1
                        || freq[maxFreq - 1] * (maxFreq - 1) + maxFreq == i + 1
                ) {
                    maxLength = i + 1;
                }
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}