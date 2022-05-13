package cn;

//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1610 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liuchenguang002
 */
public class SlidingWindowMaximum_239 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] max = solution.maxSlidingWindow(nums, 3);
        for (int value : max) {
            System.out.println(value);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public int[] maxSlidingWindow(int[] nums, int k) {
            return new int[0];
        }


        /**
         * 稀疏表
         * prefixMax[i] 表示坐标i所在的分组里面，从起点到坐标i的最大值
         * suffixMax[i] 表示坐标i所在的分组里面，从坐标i到终点的最大值
         * 例如示例：nums = [1,3,-1,-3,5,3,6,7], k = 3
         * [2] 在分组[1,3,-1]里面，所以prefix[2]=3,suffix[2]=[-1]
         */
        public int[] sparseTable(int[] nums, int k) {
            int[] prefixMax = new int[nums.length];
            int[] suffixMax = new int[nums.length];
            int[] max = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                } else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                if (i == nums.length || (i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }
            for (int i = 0; i < nums.length - k + 1; i++) {
                max[i] = Math.max(prefixMax[i + k - 1], suffixMax[i]);
            }
            return max;
        }

        /**
         * 单调性队列
         */
        public int[] monotoneQuque(int[] nums, int k) {
            int[] max = new int[nums.length - k + 1];
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                while (true) {
                    if (deque.isEmpty()) {
                        deque.addFirst(i);
                        break;
                    } else {
                        if (nums[deque.getLast()] <= nums[i]) {
                            deque.removeLast();
                        } else {
                            deque.addLast(i);
                            break;
                        }
                    }
                }

                if (i + 1 >= k) {
                    while (deque.getFirst() < i + 1 - k) {
                        deque.removeFirst();
                    }
                    max[i + 1 - k] = nums[deque.getFirst()];
                }
            }
            return max;
        }

        /**
         * 解法看着没有问题，但是执行超时，这个时间复杂度应该在log(nk)
         */
        public int[] primaryVersion(int[] nums, int k) {
            // nums = [1,3,-1,-3,5,3,6,7], k = 3
            // 1
            // 3
            // 3 -1 | 3
            // 3 -1 -3 | 3
            // 5 | 5
            // 5 3 | 5
            // 6 | 6
            // 7 | 7
            int[] max = new int[nums.length - k + 1];
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                if (deque.isEmpty()) {
                    deque.addFirst(i);
                } else {
                    while (!deque.isEmpty()) {
                        if (nums[deque.getLast()] <= nums[i]) {
                            deque.removeLast();
                        } else {
                            break;
                        }
                    }
                    deque.addFirst(i);
                }
                while (deque.size() > k) {
                    deque.removeLast();
                }
                if (i + 1 >= k) {
                    int curMax = nums[deque.getFirst()];
                    // 遍历最大值
                    for (Integer index : deque) {
                        curMax = Math.max(curMax, nums[index]);
                    }
                    max[i + 1 - k] = curMax;
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}