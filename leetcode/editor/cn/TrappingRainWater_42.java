package cn;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 3635 👎 0


public class TrappingRainWater_42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution.trap(height));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp
         */
        public int trap(int[] height) {
            int n = height.length;
            int[] leftMax = new int[n];
            int[] rightMax = new int[n];
            int left = 0;
            for (int i = 0; i < height.length; i++) {
                left = Math.max(left, height[i]);
                leftMax[i] = left;
            }
            int right = 0;
            for (int i = height.length - 1; i >= 0; i--) {
                right = Math.max(right, height[i]);
                rightMax[i] = right;
            }

            int total = 0;
            for (int i = 0; i < height.length; i++) {
                total += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return total;
        }


        /**
         * 面试时想出的思路，319/322个用例通过，时间复杂度不够好，超时了；
         */
        public int version1(int[] height) {
            int max = 0;
            for (int h : height) {
                max = Math.max(max, h);
            }

            int total = 0;
            for (int h = 1; h <= max; h++) {
                for (int i = 0; i < height.length; i++) {
                    int cur = height[i];
                    if (cur < h) {
                        int left = i - 1;
                        while (left >= 0 && height[left] < h) {
                            left--;
                        }
                        if (left < 0) {
                            continue;
                        }
                        int right = i + 1;
                        while (right < height.length && height[right] < h) {
                            right++;
                        }
                        if (right >= height.length) {
                            continue;
                        }
                        // 101 123
                        total += right - left - 1;
                        i = right;
                    }
                }
            }
            return total;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}