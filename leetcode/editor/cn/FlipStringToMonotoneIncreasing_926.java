package cn;

//如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
//
// 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。 
//
// 返回使 s 单调递增的最小翻转次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "00110"
//输出：1
//解释：翻转最后一位得到 00111.
// 
//
// 示例 2： 
//
// 
//输入：s = "010110"
//输出：2
//解释：翻转得到 011111，或者是 000111。
// 
//
// 示例 3： 
//
// 
//输入：s = "00011000"
//输出：2
//解释：翻转得到 00000000。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 105 
// s[i] 为 '0' 或 '1' 
// 
// Related Topics 字符串 动态规划 
// 👍 272 👎 0


public class FlipStringToMonotoneIncreasing_926 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            int[] countOfOne = new int[n + 1];
            for (int i = 0; i < n; i++) {
                char cur = s.charAt(i);
                if (cur == '1') {
                    countOfOne[i + 1] = countOfOne[i] + 1;
                } else {
                    countOfOne[i + 1] = countOfOne[i];
                }
            }
            int min = countOfOne[n];
            // 010110
            // 0011233
            // 0232343
            int countOfZero = 0;
            for (int i = n - 1; i >= 0; i--) {
                char cur = s.charAt(i);
                if (cur == '0') {
                    countOfZero++;
                }
                min = Math.min(min, countOfOne[i] + countOfZero);
            }
            return min;
        }

        public int dp(String s) {
            int as0 = 0, as1 = 0;
            for (char cur : s.toCharArray()) {
                if (cur == '0') {
                    as1 = Math.min(as0, as1) + 1;
                } else {
                    as1 = Math.min(as1, as0);
                    as0 += 1;
                }
            }
            return Math.min(as0, as1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}