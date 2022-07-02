package cn;

//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
//
// 
//'A' -> "1"
//'B' -> "2"
//...
//'Z' -> "26" 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 1204 👎 0


public class DecodeWays_91 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            if (s.charAt(0) == '0') {
                return 0;
            }

            int f0 = 1, f1 = 1;
            char[] array = s.toCharArray();
            for (int i = 1; i < s.length(); i++) {
                int cur = 0;
                if (array[i] >= '1' && array[i] <= '9') {
                    cur += f1;
                }
                // Integer.parseInt 不能处理 01 这种异常先到零情况
                int two = Integer.parseInt(s.substring(i - 1, i + 1));
                if ((two > 0 && two <= 26) && array[i - 1] != '0') {
                    cur += f0;
                }
                if (cur == 0) {
                    return 0;
                } else {
                    f0 = f1;
                    f1 = cur;
                }
            }
            return f1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}