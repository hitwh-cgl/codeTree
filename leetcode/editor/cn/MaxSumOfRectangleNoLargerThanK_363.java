package cn;

//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -100 <= matrix[i][j] <= 100 
// -105 <= k <= 105 
// 
//
// 
//
// 进阶：如果行数远大于列数，该如何设计解决方案？ 
// Related Topics 数组 二分查找 矩阵 有序集合 前缀和 
// 👍 416 👎 0


import java.util.TreeSet;

public class MaxSumOfRectangleNoLargerThanK_363 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int m = matrix.length;
            int n = matrix[0].length;
            int max = Integer.MIN_VALUE;
            for (int start = 0; start < m; start++) {
                int[] sum = new int[n];
                for (int end = start; end < m; end++) {
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(0);
                    int total = 0;
                    for (int i = 0; i < n; i++) {
                        sum[i] += matrix[end][i];
                        total += sum[i];
                        // total - pre <= k   => pre >= total-k
                        Integer floor = set.ceiling(total - k);
                        if (floor != null) {
                            max = Math.max(max, total - floor);
                        }
                        set.add(total);
                    }
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}