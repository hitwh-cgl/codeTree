package cn;

//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同 
// Related Topics 栈 贪心 字符串 单调栈 
// 👍 663 👎 0


/**
 * 1.如果之前的元素后面还会出现，并且大于当前元素，应该删除之前的元素，并标记它们不存在；
 * 2.如果元素已经保存在单调栈里面，当前的重复元素应该删除;(因为前面元素的后续元素会大于该元素，删除之前的元素会变大)；
 */
public class RemoveDuplicateLetters_316 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.removeDuplicateLetters("dbede");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public String removeDuplicateLetters(String s) {
            boolean[] exist = new boolean[26];
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }
            // builder就是单调栈
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (!exist[cur - 'a']) {
                    while (builder.length() > 0
                            && builder.charAt(builder.length() - 1) > cur
                            && num[builder.charAt(builder.length() - 1) - 'a'] > 0) {
                        exist[builder.charAt(builder.length() - 1) - 'a'] = false;
                        builder.deleteCharAt(builder.length() - 1);
                    }
                    exist[cur - 'a'] = true;
                    builder.append(cur);
                }
                num[cur - 'a']--;
            }
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}