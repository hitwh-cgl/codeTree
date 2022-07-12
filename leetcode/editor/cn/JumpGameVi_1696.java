package cn;

//给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
//
// 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, 
//i + k)] 包含 两个端点的任意位置。 
//
// 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。 
//
// 请你返回你能得到的 最大得分 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,-1,-2,4,-7,3], k = 2
//输出：7
//解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [10,-5,-2,4,0,3], k = 3
//输出：17
//解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length, k <= 105 
// -104 <= nums[i] <= 104 
// 
// Related Topics 队列 数组 动态规划 滑动窗口 单调队列 堆（优先队列） 
// 👍 90 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class JumpGameVi_1696 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxResult(new int[]{100, -1, -100, -1, 100}, 2);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int dp(int[] nums, int k) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = nums[0];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 1; j <= k && i + j < nums.length; j++) {
                    dp[i + j] = Math.max(dp[i + j], dp[i] + nums[i + j]);
                    if (dp[i + j] >= dp[i]) {
                        break;
                    }
                }
            }
            return dp[nums.length - 1];
        }

        public int maxResult(int[] nums, int k) {
            Deque<int[]> deque = new ArrayDeque<>();
            deque.offer(new int[]{nums[0], 0});
            for (int i = 1; i < nums.length - 1; i++) {
                int[] cur = deque.peek();
                int next = nums[i] + cur[0];
                // 删除队首比新的值小的元素
                // 删除队尾比新的值小的元素
                // 将新的值插入队尾
                // 如果队列长度大于等于k，滑动窗口移除队首元素
                while (!deque.isEmpty() && deque.peek()[0] <= next) {
                    deque.remove();
                }
                while (!deque.isEmpty() && deque.peekLast()[0] <= next) {
                    deque.removeLast();
                }
                deque.offer(new int[]{next, i});
                if (i >= k) {
                    if (!deque.isEmpty() && deque.peek()[1] <= i - k) {
                        deque.remove();
                    }
                }
            }
            return deque.peek()[0] + nums[nums.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}