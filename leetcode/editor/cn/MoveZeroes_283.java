package cn;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 104 
// -231 <= nums[i] <= 231 - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
// Related Topics 数组 双指针 
// 👍 1596 👎 0


public class MoveZeroes_283 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 0, 3, 12};
        solution.twoPointers(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * my solution was familiar with insert sort, the time cast is O(n2)
         */
        public void moveZeroes(int[] nums) {
            int right = nums.length - 1;
            for (int i = 0; i < right; i++) {
                if (nums[i] == 0) {
                    for (int j = i; j < right; j++) {
                        nums[j] = nums[j + 1];
                    }
                    nums[right--] = 0;
                    // note: current element was replaced by the next element, so we should decrease 1;
                    i--;
                }
            }
        }

        /**
         * use two pointers to reduce time cost;
         * the left pointer refer to zero, the right pointer refer to non-zero;
         * it is more like fast and slow pointer;
         */
        public void twoPointers(int[] nums) {
            int left = 0, right = left + 1;
            while (right < nums.length) {
                if (nums[left] == 0) {
                    while (right < nums.length && nums[right] == 0) {
                        right++;
                    }
                    if (right < nums.length) {
                        swap(nums, left, right);
                        left++;
                        right++;
                    }
                } else {
                    left++;
                    right++;
                }
            }
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        public void logn(int[] nums) {
            // 1 0 1 0 4 5
            int step = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    if (step != i) {
                        nums[step] = nums[i];
                    }
                    step++;
                }
            }
            for (int i = step; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}