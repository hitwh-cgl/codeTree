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


/**
 * 优化 从53行代码精简到30行，性能3ms->2ms
 *
 * @author 17862
 */
public class NumberOfIslands_200 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m;
        int n;

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        depthFirstSearch(i, j, grid);
                    }
                }
            }
            return count;
        }

        private void depthFirstSearch(int i, int j, char[][] grid) {
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] != '1') {
                return;
            }

            grid[i][j] = '2';
            depthFirstSearch(i + 1, j, grid);
            depthFirstSearch(i - 1, j, grid);
            depthFirstSearch(i, j + 1, grid);
            depthFirstSearch(i, j - 1, grid);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}