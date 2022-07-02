package cn;

//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 2454 👎 0


public class EditDistance_72 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minDistance("house", "ous");
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int[][] db;

        public int minDistance(String word1, String word2) {
            db = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                for (int j = 0; j <= word2.length(); j++) {
                    db[i][j] = -1;
                }
            }
            minDistance(word1, 0, word2, 0);
            return db[0][0];


        }

        private int minDistance(String word1, int index1, String word2, int index2) {
            if (db[index1][index2] != -1) {
                return db[index1][index2];
            }
            if (index1 == word1.length()) {
                db[index1][index2] = word2.length() - index2;
                return db[index1][index2];
            }
            if (index2 == word2.length()) {
                db[index1][index2] = word1.length() - index1;
                return db[index1][index2];
            }

            if (word1.charAt(index1) == word2.charAt(index2)) {
                db[index1][index2] = minDistance(word1, index1 + 1, word2, index2 + 1);
            } else {
                int min = minDistance(word1, index1 + 1, word2, index2 + 1) + 1;
                min = Math.min(min, minDistance(word1, index1 + 1, word2, index2) + 1);
                min = Math.min(min, minDistance(word1, index1, word2, index2 + 1) + 1);
                db[index1][index2] = min;
            }
            return db[index1][index2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}