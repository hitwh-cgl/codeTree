package cn;

//给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3, 2, 1]
//输出：1
//解释：第三大的数是 1 。 
//
// 示例 2： 
//
// 
//输入：[1, 2]
//输出：2
//解释：第三大的数不存在, 所以返回最大的数 2 。
// 
//
// 示例 3： 
//
// 
//输入：[2, 2, 3, 1]
//输出：1
//解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
//此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -231 <= nums[i] <= 231 - 1 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？ 
// Related Topics 数组 排序 
// 👍 361 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThirdMaximumNumber_414 {
    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 1};
        Solution solution = new Solution();
        solution.thirdMax(arr);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int thirdMax(int[] nums) {
            List<Integer> max = new ArrayList<>(3);
            for (int i = 0; i < 3; i++) {
                int cur = nums[0];
                for (int j = 0; j < nums.length; j++) {
                    if (max.contains(cur)) {
                        cur = nums[j];
                    } else if (!max.contains(nums[j]) && nums[j] > cur) {
                        cur = nums[j];
                    }
                }
                if (!max.contains(cur)) {
                    max.add(cur);
                }
            }
            return max.size() == 3 ? max.get(2) : max.get(0);
        }

        private int thirdMax2(int[] nums) {
            if (nums.length < 3) {
                return IntStream.of(nums).max().orElse(0);
            } else {
                int max1 = nums[0], max2 = nums[1], max3 = nums[2], temp;
                for (int i = 3; i < nums.length; i++) {
                    if (nums[i] > max1) {
                        temp = max1;
                        max1 = nums[i];
                        nums[i] = temp;
                    }
                    if (nums[i] > max2) {
                        temp = max2;
                        max2 = nums[i];
                        nums[i] = temp;
                    }
                    if (nums[i] > max3) {
                        temp = max3;
                        max3 = nums[i];
                        nums[i] = temp;
                    }
                }
                return max1 > max2 && max2 > max3 ?
                        max3 : max1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}