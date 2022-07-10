package cn;

//一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
//
// 
// 0 表示这个格子是空的，所以你可以穿过它。 
// 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。 
// -1 表示这个格子里有荆棘，挡着你的路。 
// 
//
// 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃： 
//
// 
// 从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）； 
// 当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子； 
// 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）； 
// 如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。 
// 
//
// 示例 1: 
//
// 
//输入: grid =
//[[0, 1, -1],
// [1, 0, -1],
// [1, 1,  1]]
//输出: 5
//解释： 
//玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。
//在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
//接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。
//在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。
// 
//
// 说明: 
//
// 
// grid 是一个 N * N 的二维数组，N的取值范围是1 <= N <= 50。 
// 每一个 grid[i][j] 都是集合 {-1, 0, 1}其中的一个数。 
// 可以保证起点 grid[0][0] 和终点 grid[N-1][N-1] 的值都不会是 -1。 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 278 👎 0


import java.util.Arrays;

/**
 * @author 17862
 */
public class CherryPickup_741 {
    public static void main(String[] args) {
        int[][] gird = new int[][]
                {{1, 1, -1},
                        {1, -1, 1},
                        {-1, 1, 1}};
        Solution solution = new Solution();
        int i = solution.cherryPickup(gird);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int cherryPickup(int[][] grid) {
            int n = grid.length;
            int[][][] dp = new int[2 * n - 1][n][n];
            for (int i = 0; i <= 2 * n - 2; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(dp[i][j], Integer.MIN_VALUE);
                }
            }
            dp[0][0][0] = grid[0][0];
            for (int k = 1; k <= 2 * n - 2; k++) {
                for (int x1 = Math.max(k - n + 1, 0); x1 <= Math.min(n - 1, k); x1++) {
                    int y1 = k - x1;
                    if (grid[x1][y1] == -1) {
                        continue;
                    }
                    for (int x2 = x1; x2 <= Math.min(n - 1, k); x2++) {
                        int y2 = k - x2;
                        if (grid[x2][y2] == -1) {
                            continue;
                        }
                        dp[k][x1][x2] = dp[k - 1][x1][x2];
                        if (x1 > 0) {
                            dp[k][x1][x2] = Math.max(dp[k][x1][x2], dp[k - 1][x1 - 1][x2]);
                        }
                        if (x2 > 0) {
                            dp[k][x1][x2] = Math.max(dp[k][x1][x2], dp[k - 1][x1][x2 - 1]);
                        }
                        if (x1 > 0 && x2 > 0) {
                            dp[k][x1][x2] = Math.max(dp[k][x1][x2], dp[k - 1][x1 - 1][x2 - 1]);
                        }

                        dp[k][x1][x2] += grid[x1][y1];
                        if (x1 != x2) {
                            dp[k][x1][x2] += grid[x2][y2];
                        }
                    }
                }
            }
            return Math.max(dp[2 * n - 2][n - 1][n - 1], 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}