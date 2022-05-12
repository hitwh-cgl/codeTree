package cn;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1822 👎 0


import java.util.Map;
import java.util.Stack;

public class LongestValidParentheses_32 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            return 0;
        }

        private int dp(String s) {
            int length = s.length();
            int[] dp = new int[length];
            int max = 0;
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    dp[i] = 0;
                    continue;
                }
                // (
                // () i=2 0 1-dp[0]-1
                // (()) i=3 0 0 2 3-dp[2]-1
                // ()() i=3 0 2 0 3-dp[2]-2
                // ()(()) i=5 0 2 0 0 2 5-dp[4]-2
                if (s.charAt(i) == '(') {
                    dp[i] = 0;
                } else {
                    int lastMayMatchIndex = i - dp[i - 1] - 1;
                    if (lastMayMatchIndex >= 0 && s.charAt(lastMayMatchIndex) == '(') {
                        dp[i] = i - lastMayMatchIndex + 1;
                        if (lastMayMatchIndex - 1 >= 0 && dp[lastMayMatchIndex - 1] > 0) {
                            dp[i] += dp[lastMayMatchIndex - 1];
                        }
                        max = Math.max(max, dp[i]);
                    } else {
                        dp[i] = 0;
                    }
                }
            }
            return max;
        }

        private int stack(String s) {
            int max = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (cur == '(') {
                    stack.push(i);
                } else {
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        stack.pop();
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}