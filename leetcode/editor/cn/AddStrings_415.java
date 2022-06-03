package cn;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。 
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。 
//
// 
//
// 示例 1： 
//
// 
//输入：num1 = "11", num2 = "123"
//输出："134"
// 
//
// 示例 2： 
//
// 
//输入：num1 = "456", num2 = "77"
//输出："533"
// 
//
// 示例 3： 
//
// 
//输入：num1 = "0", num2 = "0"
//输出："0"
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 104 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 
// Related Topics 数学 字符串 模拟 
// 👍 567 👎 0


public class AddStrings_415 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addStrings(String num1, String num2) {
            int l1 = num1.length();
            int l2 = num2.length();
            StringBuilder builder = new StringBuilder();
            int carry = 0;
            for (int i1 = l1 - 1, i2 = l2 - 1; i1 >= 0 || i2 >= 0; ) {
                int cur = 0;
                if (i1 >= 0) {
                    cur += num1.charAt(i1) - '0';
                    i1--;
                }
                if (i2 >= 0) {
                    cur += num2.charAt(i2) - '0';
                    i2--;
                }
                cur += carry;
                carry = cur / 10;
                builder.append(cur % 10);
            }
            if (carry != 0) {
                builder.append(carry);
            }

            return builder.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}