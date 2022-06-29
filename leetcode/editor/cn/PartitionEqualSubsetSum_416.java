package cn;

//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 1325 👎 0


import java.util.HashSet;
import java.util.Set;

public class PartitionEqualSubsetSum_416 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 5};
        Solution solution = new Solution();
        solution.dp2(nums);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartition(int[] nums) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2;
            Set<Integer> set = new HashSet<>();
            set.add(0);
            for (int num : nums) {
                int curTarget = target - num;
                if (set.contains(curTarget)) {
                    return true;
                } else {
                    Set<Integer> next = new HashSet<>();
                    next.add(num);
                    for (int value : set) {
                        next.add(value);
                        if (value + num < target) {
                            next.add(value + num);
                        }
                    }
                    set = next;
                }
            }
            return false;
        }

        /**
         * 当时不想这么写，因为担心dp数据过大会造成内存溢出，但是显然这个要快一点；
         * 结果又快占用内存又少，无语了，HasHMap这么垃圾嘛。。。
         */
        public boolean dp(int[] nums) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2;
            boolean[] match = new boolean[target + 1];
            match[0] = true;

            for (int num : nums) {
                for (int i = target; i >= num; i--) {
                    match[i] |= match[i - num];
                }
            }
            return match[target];
        }

        /**
         * 优化部分时间 7ms->6ms
         */
        public boolean dp2(int[] nums) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2;
            boolean[] match = new boolean[target + 1];
            match[0] = true;
            int max = 0;

            for (int num : nums) {
                for (int i = Math.min(max, target - num); i >= 0; i--) {
                    if (i + num <= target && match[i]) {
                        match[i + num] = true;
                        if (i + num == target) {
                            return true;
                        }
                    }
                }
                max += num;
            }
            return match[target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}