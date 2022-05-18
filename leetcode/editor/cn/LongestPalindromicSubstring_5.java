package cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 5224 👎 0


public class LongestPalindromicSubstring_5 {
    //leetcode submit region begin(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestPalindrome("babad");
    }

    static class Solution {
        /**
         * 类似中心扩散，从两头聚拢
         */
        public String longestPalindrome(String s) {
            int length = s.length();
            String sub = "" + s.charAt(0);
            for (int i = length; i > 1; i--) {
                for (int index = 0; index <= length - i; index++) {
                    if (valid(s, index, index + i - 1, i / 2)) {
                        return s.substring(index, index + i);
                    }
                }
            }
            return sub;
        }

        private boolean valid(String s, int start, int end, int step) {
            while (step-- > 0) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }


        /**
         * 中心扩散 这个比较简单和利于理解
         */
        public String longestPalindromeCenter(String s) {
            int length = s.length();
            int max = 0;
            String sub = "";
            // 奇数长度
            for (int i = 0; i < length; i++) {
                int cur = expand(s, i, i);
                if (cur > max) {
                    max = cur;
                    // abcbc i=2 | 3
                    int offset = (cur - 1) / 2;
                    sub = s.substring(i - offset, i + offset + 1);
                }
            }
            // 偶数长度
            for (int i = 1; i < length; i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    int cur = expand(s, i - 1, i);
                    if (cur > max) {
                        max = cur;
                        // abba i=2 | 4
                        int offset = cur / 2;
                        sub = s.substring(i - offset, i + offset);
                    }
                }
            }
            return sub;
        }

        private int expand(String s, int left, int right) {
            while (left - 1 >= 0 && right + 1 < s.length()
                    && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }
            return right - left + 1;
        }

        /**
         * 动态规划
         * 1.match[start][end]: 下标start到end的子串是否是回文串
         * 2.match[start][end] = s.charAt(start)==s.charAt(end) && match[start+1][end-1]
         */
        public static String longestPalindromeDp(String s) {
            int maxLen = 0;
            String maxPal = "";
            boolean[][] match = new boolean[s.length()][s.length()];
            for (int len = 1; len <= s.length(); len++) {
                for (int start = 0; start <= s.length() - len; start++) {
                    int end = start + len - 1;
                    match[start][end] = s.charAt(start) == s.charAt(end)
                            && (len == 1 || len == 2 || match[start + 1][end - 1]);
                    if (match[start][end] && len > maxLen) {
                        maxLen = len;
                        maxPal = s.substring(start, end + 1);
                    }
                }
            }
            return maxPal;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}