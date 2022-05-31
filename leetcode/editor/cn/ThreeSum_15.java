package cn;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 4428 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1.重复元素去重问题，比如 [-1,-1,0,1],但是直接去除重复元素也有问题 [-1,-1,2]
 * 2.三数，两个不变，另一个肯定不变，就会重复
 * 3.重复问题，主要考虑三个点重复的问题，用StringSet判重会超时
 * 4.边界值问题，数组过短/排序后端点值
 */
public class ThreeSum_15 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // [-2,0,1,1,2]
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return ans;
            }
            Arrays.sort(nums);
            if (nums[0] > 0 || nums[nums.length - 1] < 0) {
                return ans;
            }
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int temp = nums[i] + nums[left] + nums[right];
                    if (temp == 0) {
                        List<Integer> res = new ArrayList<>(3);
                        res.add(nums[i]);
                        res.add(nums[left]);
                        res.add(nums[right]);
                        ans.add(res);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (temp > 0) {
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else {
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}