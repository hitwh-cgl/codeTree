package cn;

//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 
// 👍 818 👎 0


public class AddBinary_67 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            int al = a.length();
            int bl = b.length();
            StringBuilder builder = new StringBuilder();
            int flag = 0;
            for (int ac = al - 1, bc = bl - 1; ac >= 0 || bc >= 0; ac--, bc--) {
                int count = 0;
                if (ac >= 0 && a.charAt(ac) == '1') {
                    count++;
                }
                if (bc >= 0 && b.charAt(bc) == '1') {
                    count++;
                }
                count += flag;
                flag = count / 2;
                count = count % 2;
                builder.append(count);
            }

            if (flag == 1) {
                builder.append(flag);
            }

            return builder.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}