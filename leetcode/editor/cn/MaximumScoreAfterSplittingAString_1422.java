package cn;

//给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
//
//
// 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。 
//
// 
//
// 示例 1： 
//
// 输入：s = "011101"
//输出：5 
//解释：
//将字符串 s 划分为两个非空子字符串的可行方案有：
//左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5 
//左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4 
//左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3 
//左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2 
//左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
// 
//
// 示例 2： 
//
// 输入：s = "00111"
//输出：5
//解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 2 <= s.length <= 500 
// 字符串 s 仅由字符 '0' 和 '1' 组成。 
// 
// Related Topics 字符串 
// 👍 75 👎 0


/**
 * 注意：1.运算符优先级 2.边界值
 *
 * @author 17862
 */
public class MaximumScoreAfterSplittingAString_1422 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxScore("1111");
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxScore(String s) {
            int n = s.length();
            int[] count = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                // + 优先级高于 == , == 高于 ?:
                // 这样写会悲剧，捂脸 count[i] = count[i - 1] + s.charAt(i - 1) == '0' ? 1 : 0;
                count[i] += count[i - 1];
                count[i] += s.charAt(i - 1) == '0' ? 1 : 0;
            }
            int countOfOne = 0;
            int max = 0;
            for (int i = n - 1; i >= 1; i--) {
                countOfOne += s.charAt(i) == '1' ? 1 : 0;
                max = Math.max(max, count[i] + countOfOne);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}