package cn;

//给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 ma
//t[r][c] 的和： 
//
// 
// i - k <= r <= i + k, 
// j - k <= c <= j + k 且 
// (r, c) 在矩阵内。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//输出：[[12,21,16],[27,45,33],[24,39,28]]
// 
//
// 示例 2： 
//
// 
//输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
//输出：[[45,45,45],[45,45,45],[45,45,45]]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n, k <= 100 
// 1 <= mat[i][j] <= 100 
// 
// Related Topics 数组 矩阵 前缀和 
// 👍 142 👎 0


public class MatrixBlockSum_1314 {
    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution solution = new Solution();
        int[][] result = solution.matrixBlockSum(mat, 1);
        System.out.println(result.length);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] matrixBlockSum(int[][] mat, int k) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] sum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = mat[i - 1][j - 1] + sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
                }
            }

            int[][] answer = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int x1 = Math.min(i + k + 1, m);
                    int y1 = Math.min(j + k + 1, n);
                    int x2 = Math.max(0, i - k);
                    int y2 = Math.max(0, j - k);
                    answer[i][j] = sum[x1][y1] - sum[x1][y2] - sum[x2][y1] + sum[x2][y2];
                }
            }
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}