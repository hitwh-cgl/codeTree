package cn;

//给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
//
// 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。 
// 
//
// 示例 1： 
//
// 
//
// 
//输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//输出：2
//解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], thresh
//old = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n <= 300 
// 0 <= mat[i][j] <= 104 
// 0 <= threshold <= 105 
// 
// Related Topics 数组 二分查找 矩阵 前缀和 
// 👍 98 👎 0


/**
 * 1.类似LC363中处理二维矩阵的前缀和手法，可以在O(m*m*n)的时间复杂度内计算出结果，空间复杂度是O(m+n)
 * 2.更重要的手法是二维前缀和的记忆化存储手法，可以在O(mn)的时间内计算所有矩形数字和，空间复杂度是O(m*n)
 *
 * @author 17862
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold_1292 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSideLength(int[][] mat, int threshold) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] sum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
                }
            }

            int max = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int c = max + 1; c <= Math.max(m, n); c++) {
                        if (i < c || j < c) {
                            break;
                        }
                        int target = sum[i][j] - sum[i - c][j] - sum[i][j - c] + sum[i - c][j - c];
                        if (target <= threshold) {
                            max++;
                        } else {
                            break;
                        }
                    }
                }
            }
            return max;
        }

        public int version1(int[][] mat, int threshold) {
            int m = mat.length;
            int n = mat[0].length;
            int maxLength = 0;
            for (int start = 0; start < m; start++) {
                int[] sum = new int[n];
                for (int end = start; end < m; end++) {
                    int length = end - start + 1;
                    int total = 0;
                    int[] totalSum = new int[n];
                    for (int i = 0; i < n; i++) {
                        sum[i] += mat[end][i];
                        total += sum[i];
                        totalSum[i] = total;
                        int pre = i - length;
                        if (pre >= 0 && totalSum[i] - totalSum[pre] <= threshold) {
                            maxLength = Math.max(maxLength, length);
                        } else if (pre == -1 && totalSum[i] <= threshold) {
                            maxLength = Math.max(maxLength, length);
                        }
                    }
                }
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}