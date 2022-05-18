package cn;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 2645 👎 0


import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses_22 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 递归的逻辑很拧巴，我改成了迭代逻辑，感觉舒服了很多;
         * 1.任何合法的括号组合都可以改写成 (a)b, a和b都是合法的括号组合;
         * 2.另一个担心是重复问题，例如：(a1)b1和(a2)b2重复,但是a1a2不相同，b1b2不相同；
         * 假如a1长度比a2长，那么(a1)包含(a2)=>(a1)=(a2)(c)=>a1=a2)(，不可能；
         * 加入a1和a2一样长，那么a1=a2，和假设不符；
         */
        public List<String> generateParenthesis(int n) {
            List<String>[] cache = new ArrayList[10];
            List<String> cache0 = new ArrayList<>(1);
            cache0.add("");
            cache[0] = cache0;
            List<String> cache1 = new ArrayList<>(1);
            cache1.add("()");
            cache[1] = cache1;

            for (int i = 2; i <= n; i++) {
                // 求i对括号生成
                List<String> target = new ArrayList<>();
                for (int c = 0; c < i; c++) {
                    for (String left : cache[c]) {
                        for (String right : cache[i - 1 - c]) {
                            target.add("(" + left + ")" + right);
                        }
                    }
                }
                cache[i] = target;
            }
            return cache[n];
        }

        public List<String> generateParenthesisByTrack(int n) {
            List<String> ans = new ArrayList<>();
            backtrack(ans, new StringBuilder(), 0, 0, n);
            return ans;
        }

        public void backtrack(List<String> ans, StringBuilder builder, int open, int close, int max) {
            if (builder.length() == max * 2) {
                ans.add(builder.toString());
                return;
            }

            if (open < max) {
                builder.append("(");
                backtrack(ans, builder, open + 1, close, max);
                builder.deleteCharAt(builder.length() - 1);
            }
            // notice
            if (close < open) {
                builder.append(")");
                backtrack(ans, builder, open, close + 1, max);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}