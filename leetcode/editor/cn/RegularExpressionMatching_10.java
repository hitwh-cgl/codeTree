package cn;

//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 
// 👍 2984 👎 0


/**
 * @author liuchenguang002
 */
public class RegularExpressionMatching_10 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("ab", ".*c"));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        private int[][] db;

        public boolean isMatch(String s, String p) {
            int l1 = s.length();
            int l2 = p.length();
            // db[i][j] = s.substring(i) match p.substring(j);
            db = new int[l1 + 1][l2 + 1];
            db[l1][l2] = 1;
            isMatch(s, 0, p, 0);
            return db[0][0] == 1;
        }

        private int isMatch(String s, int index1, String p, int index2) {
            if (db[index1][index2] != 0) {
                return db[index1][index2];
            }

            // 匹配*
            if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*') {
                int next = isMatch(s, index1, p, index2 + 2);
                int index1Temp = index1;
                while (next != 1 && index1Temp < s.length() && match(s.charAt(index1Temp), p.charAt(index2))) {
                    next = isMatch(s, index1Temp + 1, p, index2 + 2);
                    index1Temp++;
                }
                db[index1][index2] = next;
                return db[index1][index2];
            }

            if (index2 == p.length() && index1 < s.length()) {
                // 字符串没有匹配完
                db[index1][index2] = -1;
                return db[index1][index2];
            }
            if (index1 == s.length() && index2 < p.length()) {
                // 字符串不够匹配
                db[index1][index2] = -1;
                return db[index1][index2];
            }

            // 匹配.
            // 匹配单个字符
            if (match(s.charAt(index1), p.charAt(index2))) {
                int next = isMatch(s, index1 + 1, p, index2 + 1);
                db[index1][index2] = next;
                return db[index1][index2];
            } else {
                db[index1][index2] = -1;
                return db[index1][index2];
            }
        }

        private boolean match(char sc, char pc) {
            if (pc == '.') {
                return true;
            } else {
                return sc == pc;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}