package cn;

//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 
//
// 回文字符串 是正着读和倒过来读一样的字符串。 
//
// 子字符串 是字符串中的由连续字符组成的一个序列。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 871 👎 0


/**
 * 1.toCharArray直接通过数组访问比通过charAt要快，559ms->146ms 11ms->6ms
 */
public class PalindromicSubstrings_647 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.dp("aaa");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 类似中心扩散，从两头聚拢
         */
        public int countSubstrings(String s) {
            int length = s.length();
            char[] chars = s.toCharArray();
            int count = 0;
            // i is the length of palindromic subString
            for (int i = 1; i <= length; i++) {
                for (int index = 0; index <= length - i; index++) {
                    if (valid(chars, index, index + i - 1, i / 2)) {
                        count++;
                    }
                }
            }
            return count;
        }

        // ababa 0 4 2
        public boolean valid(char[] chars, int startIndex, int endIndex, int step) {
            while (step-- > 0) {
                if (chars[startIndex++] != chars[endIndex--]) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 动态规划
         */
        public int dp(String s) {
            int length = s.length();
            boolean[][] match = new boolean[length][length];
            int count = 0;
            char[] chars = s.toCharArray();
            for (int i = 1; i <= length; i++) {
                for (int index = 0; index <= length - i; index++) {
                    int end = index + i - 1;
                    if (chars[index] == chars[end] && (i == 1 || i == 2 || match[index + 1][end - 1])) {
                        match[index][end] = true;
                        count++;
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}