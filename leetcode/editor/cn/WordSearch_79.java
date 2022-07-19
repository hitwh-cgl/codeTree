package cn;

//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 1324 👎 0


/**
 * todo 考虑怎么使用数据结构实现递归
 *
 * @author 17862
 */
public class WordSearch_79 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        solution.exist(board, word);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m, n;

        /**
         * 通过递归实现的深度优先搜索
         */
        public boolean exist(char[][] board, String word) {
            char start = word.charAt(0);
            m = board.length;
            n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == start) {
                        if (word.length() == 1) {
                            return true;
                        }
                        boolean[][] visit = new boolean[m][n];
                        visit[i][j] = true;
                        boolean success = recursion(board, i - 1, j, word, 1, visit)
                                || recursion(board, i + 1, j, word, 1, visit)
                                || recursion(board, i, j + 1, word, 1, visit)
                                || recursion(board, i, j - 1, word, 1, visit);
                        if (success) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean recursion(char[][] board, int i, int j, String word, int index, boolean[][] visit) {
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return false;
            }
            boolean match = board[i][j] == word.charAt(index) && !visit[i][j];
            if (!match) {
                return false;
            }
            visit[i][j] = true;
            if (index == word.length() - 1) {
                return true;
            }
            boolean success = recursion(board, i - 1, j, word, index + 1, visit)
                    || recursion(board, i + 1, j, word, index + 1, visit)
                    || recursion(board, i, j + 1, word, index + 1, visit)
                    || recursion(board, i, j - 1, word, index + 1, visit);
            if (!success) {
                visit[i][j] = false;
            }
            return success;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}