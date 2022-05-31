package cn;

//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 1155 👎 0


public class MaximalSquare_221 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] max = new int[m][n];
            int maxLength = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        if (i == 0 || j == 0) {
                            max[i][j] = 1;
                        } else {
                            max[i][j] = Math.min(max[i - 1][j - 1], max[i - 1][j]);
                            max[i][j] = Math.min(max[i][j - 1], max[i][j]);
                            max[i][j] = max[i][j] + 1;
                        }
                        maxLength = Math.max(maxLength, max[i][j]);
                    }
                }
            }
            return maxLength * maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}