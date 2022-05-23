package cn;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1735 👎 0


public class NumberOfIslands_200 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int count = 0;
            boolean[][] visit = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        if (grid[i][j] == '1') {
                            count++;
                            visit[i][j] = true;
                            // 广度优先搜索
                            breadthFirstSearch(i, j, grid, visit);
                        } else {
                            visit[i][j] = true;
                        }
                    }
                }
            }
            return count;
        }

        // 一开始寄希望于只往右下两个方向遍历来减少重复探索，但是其实需要往左边和右边探索的情况；
        // 1 1 1
        // 0 1 0
        // 1 1 0 例如左边的这个1；
        private void breadthFirstSearch(int i, int j, char[][] grid, boolean[][] visit) {
            int m = grid.length;
            int n = grid[0].length;

            if (i + 1 < m && !visit[i + 1][j]) {
                visit[i + 1][j] = true;
                if (grid[i + 1][j] == '1') {
                    breadthFirstSearch(i + 1, j, grid, visit);
                }
            }
            if (j + 1 < n && !visit[i][j + 1]) {
                visit[i][j + 1] = true;
                if (grid[i][j + 1] == '1') {
                    breadthFirstSearch(i, j + 1, grid, visit);
                }
            }
            if (i - 1 >= 0 && !visit[i - 1][j]) {
                visit[i - 1][j] = true;
                if (grid[i - 1][j] == '1') {
                    breadthFirstSearch(i - 1, j, grid, visit);
                }
            }
            if (j - 1 >= 0 && !visit[i][j - 1]) {
                visit[i][j - 1] = true;
                if (grid[i][j - 1] == '1') {
                    breadthFirstSearch(i, j - 1, grid, visit);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}