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
 * 1.使用StringBuilder进行deleteCharAt时发现元素下标会变
 * 2.元素该不该删除会随着后面元素删除变化，比如 bcabc, 第一次c删除后第一个b变成了应该删除。
 */
public class RemoveDuplicateLetters_316 {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            boolean[] flag = new boolean[26];
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                // 这个字母位置没有确定
                if (!flag[ch - 'a']) {
                    while (builder.length() > 0 && builder.charAt(builder.length() - 1) > ch) {
                        if (num[builder.charAt(builder.length() - 1) - 'a'] > 0) {
                            flag[builder.charAt(builder.length() - 1) - 'a'] = false;
                            builder.deleteCharAt(builder.length() - 1);
                        } else {
                            break;
                        }
                    }
                    flag[ch - 'a'] = true;
                    builder.append(ch);
                }
                num[ch - 'a']--;
            }
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}