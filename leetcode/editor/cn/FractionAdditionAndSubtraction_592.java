package cn;

//给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。
//
// 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为
// 2/1。 
//
// 
//
// 示例 1: 
//
// 
//输入: expression = "-1/2+1/2"
//输出: "0/1"
// 
//
// 示例 2: 
//
// 
//输入: expression = "-1/2+1/2+1/3"
//输出: "1/3"
// 
//
// 示例 3: 
//
// 
//输入: expression = "1/3-1/2"
//输出: "-1/6"
// 
//
// 
//
// 提示: 
//
// 
// 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
// 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。 
// 输入只包含合法的最简分数，每个分数的分子与分母的范围是 [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。 
// 输入的分数个数范围是 [1,10]。 
// 最终结果的分子与分母保证是 32 位整数范围内的有效整数。 
// 
// Related Topics 数学 字符串 模拟 
// 👍 102 👎 0


/**
 * @author 17862
 */
public class FractionAdditionAndSubtraction_592 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.fractionAddition("1/3-1/2");
        System.out.println(s);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String fractionAddition(String expression) {
            int[][] value = parse(expression);
            int[] cur = value[0];
            for (int i = 1; i < value.length; i++) {
                cur = merge(cur, value[i]);
            }
            cur = divide(cur);
            if (cur[1] == 0) {
                cur[2] = 1;
            }
            if (cur[0] == 1) {
                return cur[1] + "/" + cur[2];
            } else {
                return "-" + cur[1] + "/" + cur[2];
            }
        }

        private int[][] parse(String expression) {
            if (Character.isDigit(expression.charAt(0))) {
                expression = "+" + expression;
            }
            int length = expression.split("/").length - 1;
            int[][] value = new int[length][3];
            int index = 0;
            for (int i = 0; i < length; i++) {
                int[] cur = value[i];
                // 符号
                if (expression.charAt(index) == '+') {
                    cur[0] = 1;
                } else {
                    cur[0] = -1;
                }
                index++;
                // 分子
                if (index + 1 < expression.length() && Character.isDigit(expression.charAt(index + 1))) {
                    cur[1] = (expression.charAt(index) - '0') * 10 + expression.charAt(index + 1) - '0';
                    index += 2;
                } else {
                    cur[1] = expression.charAt(index) - '0';
                    index += 1;
                }
                index++;
                // 分母
                if (index + 1 < expression.length() && Character.isDigit(expression.charAt(index + 1))) {
                    cur[2] = (expression.charAt(index) - '0') * 10 + expression.charAt(index + 1) - '0';
                    index += 2;
                } else {
                    cur[2] = expression.charAt(index) - '0';
                    index += 1;
                }
            }
            return value;
        }

        private int[] merge(int[] left, int[] right) {
            if (left[0] == right[0]) {
                if (left[2] == right[2]) {
                    return new int[]{left[0], left[1] + right[1], left[2]};
                } else {
                    return new int[]{left[0], left[1] * right[2] + left[2] * right[1], left[2] * right[2]};
                }
            } else {
                int[] cur;
                if (left[2] == right[2]) {
                    cur = new int[]{left[0], left[0] * left[1] + right[0] * right[1], left[2]};
                } else {
                    cur = new int[]{left[0], left[0] * left[1] * right[2] + right[0] * left[2] * right[1], left[2] * right[2]};
                }
                if (cur[1] >= 0) {
                    cur[0] = 1;
                } else {
                    cur[0] = -1;
                    cur[1] = -cur[1];
                }
                return cur;
            }
        }

        private int[] divide(int[] value) {
            int min = Math.min(value[1], value[2]);
            for (int i = 2; i <= min; i++) {
                if (value[1] % i == 0 && value[2] % i == 0) {
                    value[1] /= i;
                    value[2] /= i;
                    min /= i;
                    i--;
                }
            }
            return value;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}