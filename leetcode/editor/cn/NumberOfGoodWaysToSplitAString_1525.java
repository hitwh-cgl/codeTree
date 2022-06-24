package cn;

//给你一个字符串 s ，一个分割被称为 「好分割」 当它满足：将 s 分割成 2 个字符串 p 和 q ，它们连接起来等于 s 且 p 和 q 中不同字符的数
//目相同。 
//
// 请你返回 s 中好分割的数目。 
//
// 
//
// 示例 1： 
//
// 输入：s = "aacaba"
//输出：2
//解释：总共有 5 种分割字符串 "aacaba" 的方法，其中 2 种是好分割。
//("a", "acaba") 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。
//("aa", "caba") 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。
//("aac", "aba") 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。
//("aaca", "ba") 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。
//("aacab", "a") 左边字符串和右边字符串分别包含 3 个和 1 个不同的字符。
// 
//
// 示例 2： 
//
// 输入：s = "abcd"
//输出：1
//解释：好分割为将字符串分割成 ("ab", "cd") 。
// 
//
// 示例 3： 
//
// 输入：s = "aaaaa"
//输出：4
//解释：所有分割都是好分割。 
//
// 示例 4： 
//
// 输入：s = "acbadbaada"
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// s 只包含小写英文字母。 
// 1 <= s.length <= 10^5 
// 
// Related Topics 位运算 字符串 动态规划 
// 👍 40 👎 0


import java.util.HashSet;
import java.util.Set;

public class NumberOfGoodWaysToSplitAString_1525 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSplitsV2("aacaba"));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int numSplitsV1(String s) {
            int count = 0;
            Set<Character> set = new HashSet<>();
            int[] countArray = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                set.add(s.charAt(i));
                countArray[i] = set.size();
            }
            set.clear();
            for (int i = s.length() - 1; i >= 1; i--) {
                set.add(s.charAt(i));
                if (set.size() == countArray[i - 1]) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 一顿优化猛如虎，14ms->8ms，切！
         */
        public int numSplitsV2(String s) {
            int[] leftMark = new int[26];
            int[] rightMark = new int[26];
            int[] leftCount = new int[s.length()];
            int[] rightCount = new int[s.length()];
            int left = 0, right = 0;
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                char leftChar = s.charAt(i);
                if (leftMark[leftChar - 'a'] == 0) {
                    leftMark[leftChar - 'a']++;
                    left++;
                }
                leftCount[i] = left;
                char rightChar = s.charAt(s.length() - 1 - i);
                if (rightMark[rightChar - 'a'] == 0) {
                    rightMark[rightChar - 'a']++;
                    right++;
                }
                rightCount[s.length() - 1 - i] = right;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                if (leftCount[i] == rightCount[i + 1]) {
                    result++;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}