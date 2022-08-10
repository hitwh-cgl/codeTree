package cn;

//求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
//
// 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。 
//
// 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。 
//
// 
//
// 示例 1： 
//
// 
//输入: equation = "x+5-3+x=6+x-2"
//输出: "x=2"
// 
//
// 示例 2: 
//
// 
//输入: equation = "x=x"
//输出: "Infinite solutions"
// 
//
// 示例 3: 
//
// 
//输入: equation = "2x=x"
//输出: "x=0"
// 
//
// 
//
// 提示: 
//
// 
// 3 <= equation.length <= 1000 
// equation 只有一个 '='. 
// equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。 
// 
// Related Topics 数学 字符串 模拟 
// 👍 126 👎 0


public class SolveTheEquation_640 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solveEquation("x=2x"));
        System.out.println(solution.solveEquation("x+2=x+3"));
        System.out.println(solution.solveEquation("x+2=x+2"));
        System.out.println(solution.solveEquation("x+7=2x+8"));
        System.out.println(solution.solveEquation("x+5-3+x=6+x-2"));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String solveEquation(String equation) {
            int left = 0, right = 0;
            String[] split = equation.split("=");
            int[] leftResult = parseExpression(split[0]);
            left += leftResult[0];
            right -= leftResult[1];
            int[] rightResult = parseExpression(split[1]);
            left -= rightResult[0];
            right += rightResult[1];

            if (left == 0) {
                if (right == 0) {
                    return "Infinite solutions";
                } else {
                    return "No solution";
                }
            } else {
                int result = right == 0 ? 0 : right / left;
                return "x=" + result;
            }
        }

        private int[] parseExpression(String expression) {
            // ax+b;
            int a = 0;
            int b = 0;
            boolean positive = true;
            int length = expression.length();
            for (int i = 0; i < length; i++) {
                int cur;
                if (Character.isDigit(expression.charAt(i))) {
                    cur = expression.charAt(i) - '0';
                    while (i + 1 < length && Character.isDigit(expression.charAt(i + 1))) {
                        i++;
                        cur = cur * 10 + expression.charAt(i) - '0';
                    }
                    if (i + 1 < length && expression.charAt(i + 1) == 'x') {
                        if (positive) {
                            a += cur;
                        } else {
                            a -= cur;
                        }
                        i++;
                    } else {
                        if (positive) {
                            b += cur;
                        } else {
                            b -= cur;
                        }
                    }
                } else {
                    if (expression.charAt(i) == '-') {
                        positive = false;
                    } else if (expression.charAt(i) == 'x') {
                        if (positive) {
                            a += 1;
                        } else {
                            a -= 1;
                        }
                    } else {
                        positive = true;
                    }
                }
            }

            return new int[]{a, b};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}