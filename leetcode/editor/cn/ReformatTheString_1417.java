package cn;

//给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
//
// 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。 
//
// 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。 
//
// 
//
// 示例 1： 
//
// 输入：s = "a0b1c2"
//输出："0a1b2c"
//解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
// 
//
// 示例 2： 
//
// 输入：s = "leetcode"
//输出：""
//解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
// 
//
// 示例 3： 
//
// 输入：s = "1229857369"
//输出：""
//解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
// 
//
// 示例 4： 
//
// 输入：s = "covid2019"
//输出："c2o0v1i9d"
// 
//
// 示例 5： 
//
// 输入：s = "ab123"
//输出："1a2b3"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 仅由小写英文字母和/或数字组成。 
// 
// Related Topics 字符串 
// 👍 26 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 17862
 */
public class ReformatTheString_1417 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reformat("abc123"));
        System.out.println(solution.reformat("abc1234"));
        System.out.println(solution.reformat("abcd12"));
        System.out.println(solution.reformat("abcd123"));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reformat(String s) {
            int digitCount = 0, charCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    digitCount++;
                } else {
                    charCount++;
                }
            }
            if (Math.abs(digitCount - charCount) > 1) {
                return "";
            } else {
                char[] value = new char[s.length()];
                int digitIndex, charIndex;
                if (digitCount > charCount) {
                    digitIndex = 0;
                    charIndex = 1;
                } else {
                    charIndex = 0;
                    digitIndex = 1;
                }
                for (char c : s.toCharArray()) {
                    if (Character.isDigit(c)) {
                        value[digitIndex] = c;
                        digitIndex += 2;
                    } else {
                        value[charIndex] = c;
                        charIndex += 2;
                    }
                }
                return new String(value);
            }
        }

        public String version1(String s) {
            Deque<Character> d1 = new ArrayDeque<>();
            Deque<Character> d2 = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    d1.offer(s.charAt(i));
                } else {
                    d2.offer(s.charAt(i));
                }
            }

            if (Math.abs(d1.size() - d2.size()) > 1) {
                return "";
            } else {
                StringBuilder builder = new StringBuilder();
                if (d1.size() > d2.size()) {
                    while (!d1.isEmpty()) {
                        builder.append(d1.poll());
                        if (!d2.isEmpty()) {
                            builder.append(d2.poll());
                        }
                    }
                } else {
                    while (!d2.isEmpty()) {
                        builder.append(d2.poll());
                        if (!d1.isEmpty()) {
                            builder.append(d1.poll());
                        }
                    }
                }
                return builder.toString();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}