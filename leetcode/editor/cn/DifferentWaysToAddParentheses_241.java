package cn;

//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
//
// 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。 
//
// 
//
// 示例 1： 
//
// 
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0 
//(2-(1-1)) = 2
// 
//
// 示例 2： 
//
// 
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20 
// expression 由数字和算符 '+'、'-' 和 '*' 组成。 
// 输入表达式中的所有整数值在范围 [0, 99] 
// 
// Related Topics 递归 记忆化搜索 数学 字符串 动态规划 
// 👍 625 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses_241 {
    public static void main(String[] args) {
        String expression = "2*3-4*5";
        Solution solution = new Solution();
        solution.diffWaysToCompute(expression);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        private int numCount = 0;
        private Map<Integer, Character> operationMap = new HashMap<>();
        private Map<Integer, Integer> numMap = new HashMap<>();
        private List[][] result;

        public List<Integer> diffWaysToCompute(String expression) {
            preHandle(expression);
            result = new List[numCount][numCount];
            for (int i = 0; i < numCount; i++) {
                int curNum = numMap.get(i);
                result[i][i] = new ArrayList<Integer>();
                result[i][i].add(curNum);
                compute(0, i);
            }
            return result[0][numCount - 1];
        }

        private List<Integer> compute(int start, int end) {
            if (result[start][end] != null) {
                return result[start][end];
            }

            if (start == end - 1) {
                ArrayList<Integer> list = new ArrayList<>(1);
                int opRes = operation(operationMap.get(start), numMap.get(start), numMap.get(end));
                list.add(opRes);
                result[start][end] = list;
                return list;
            }

            List<Integer> list = new ArrayList<>();
            for (int i = start; i < end; i++) {
                char op = operationMap.get(i);
                for (int num1 : compute(start, i)) {
                    for (int num2 : compute(i + 1, end)) {
                        list.add(operation(op, num1, num2));
                    }
                }
            }
            result[start][end] = list;
            return list;
        }

        private int operation(char op, int num1, int num2) {
            if (op == '+') {
                return num1 + num2;
            } else if (op == '-') {
                return num1 - num2;
            } else {
                return num1 * num2;
            }
        }

        private void preHandle(String expression) {
            char[] exp = expression.toCharArray();
            int i = 0;
            while (i < exp.length) {
                int curNum;
                if (i + 1 < exp.length && exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    curNum = Integer.parseInt(expression.substring(i, i + 2));
                    i += 2;
                } else {
                    curNum = Integer.parseInt(expression.substring(i, i + 1));
                    i += 1;
                }
                numMap.put(numCount, curNum);
                if (i < exp.length) {
                    operationMap.put(numCount, exp[i]);
                    i += 1;
                }
                numCount++;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}