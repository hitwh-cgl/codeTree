package cn;

//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
//
// 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//输出：16
//解释：它的周长是上面图片中的 16 个黄色的边 
//
// 示例 2： 
//
// 
//输入：grid = [[1]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 565 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1.这题虽然可以用深度优先遍历实现，但是还是用迭代的方法更加灵活且容易理解；
 * 2.DFS的写法，我的版本很多坑，而且执行效率不太行
 *
 * @author 17862
 */
public class IslandPerimeter_463 {
    public static void main(String[] args) {
        int[][] grid = new int[][]
//                {{0, 1, 0, 0},
//                {1, 1, 1, 0},
//                {0, 1, 0, 0},
//                {1, 1, 0, 0}};
                {{0, 1, 1}, {1, 1, 1}};
        Solution solution = new Solution();
        int i = solution.islandPerimeter(grid);
        System.out.println(i);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int commonIteration(int[][] grid) {
            int block = 0, adjacent = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        block++;
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        adjacent++;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        adjacent++;
                    }
                }
            }
            return block * 4 - adjacent * 2;
        }


        public int islandPerimeter(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            boolean[][] visit = new boolean[row][col];

            Deque<int[]> deque = new ArrayDeque<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        deque.push(new int[]{i, j});
                        int sum = 0;
                        while (!deque.isEmpty()) {
                            int[] cur = deque.pop();
                            if (!visit[cur[0]][cur[1]]) {
                                sum += dfs(grid, visit, cur, deque);
                                visit[cur[0]][cur[1]] = true;
                            }
                        }
                        return sum;
                    }
                }
            }
            return 0;
        }

        /**
         * 遍历相邻的岛屿部分，如果没有访问就加入栈中等待后续遍历，如果已经访问就会影响周长计算；
         */
        private int dfs(int[][] grid, boolean[][] visit, int[] cur, Deque<int[]> deque) {
            int adjacentVisitedLandCount = 0;

            int row = grid.length;
            int col = grid[0].length;
            if (cur[0] + 1 < row && grid[cur[0] + 1][cur[1]] == 1) {
                if (visit[cur[0] + 1][cur[1]]) {
                    adjacentVisitedLandCount++;
                } else {
                    deque.push(new int[]{cur[0] + 1, cur[1]});
                }
            }
            if (cur[0] - 1 >= 0 && grid[cur[0] - 1][cur[1]] == 1) {
                if (visit[cur[0] - 1][cur[1]]) {
                    adjacentVisitedLandCount++;
                } else {
                    deque.push(new int[]{cur[0] - 1, cur[1]});
                }
            }
            if (cur[1] + 1 < col && grid[cur[0]][cur[1] + 1] == 1) {
                if (visit[cur[0]][cur[1] + 1]) {
                    adjacentVisitedLandCount++;
                } else {
                    deque.push(new int[]{cur[0], cur[1] + 1});
                }
            }
            if (cur[1] - 1 >= 0 && grid[cur[0]][cur[1] - 1] == 1) {
                if (visit[cur[0]][cur[1] - 1]) {
                    adjacentVisitedLandCount++;
                } else {
                    deque.push(new int[]{cur[0], cur[1] - 1});
                }
            }

            if (adjacentVisitedLandCount == 0) {
                return 4;
            } else if (adjacentVisitedLandCount == 1) {
                return 2;
            } else if (adjacentVisitedLandCount == 2) {
                return 0;
            } else if (adjacentVisitedLandCount == 3) {
                return -2;
            } else {
                return -4;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}