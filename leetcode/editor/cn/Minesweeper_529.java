package cn;

//让我们一起来玩扫雷游戏！
//
// 给你一个大小为 m x n 二维字符矩阵 board ，表示扫雷游戏的盘面，其中： 
//
// 
// 'M' 代表一个 未挖出的 地雷， 
// 'E' 代表一个 未挖出的 空方块， 
// 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块， 
// 数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻， 
// 'X' 则表示一个 已挖出的 地雷。 
// 
//
// 给你一个整数数组 click ，其中 click = [clickr, clickc] 表示在所有 未挖出的 方块（'M' 或者 'E'）中的下一个点击位
//置（clickr 是行下标，clickc 是列下标）。 
//
// 根据以下规则，返回相应位置被点击后对应的盘面： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X' 。 
// 如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的 未挖出 方块都应该被递归地揭露。 
// 如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回盘面。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],
//["E","E","E","E","E"]], click = [3,0]
//输出：[["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B"
//,"B","B","B"]]
// 
//
// 示例 2： 
//
// 
//输入：board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],
//["B","B","B","B","B"]], click = [1,2]
//输出：[["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B"
//,"B","B","B"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 50 
// board[i][j] 为 'M'、'E'、'B' 或数字 '1' 到 '8' 中的一个 
// click.length == 2 
// 0 <= clickr < m 
// 0 <= clickc < n 
// board[clickr][clickc] 为 'M' 或 'E' 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 298 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 通过判定是否是'E'来防止重复遍历
 *
 * @author 17862
 */
public class Minesweeper_529 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int row = click[0];
            int column = click[1];
            int m = board.length;
            int n = board[0].length;
            if (board[row][column] == 'M') {
                board[row][column] = 'X';
                return board;
            }

            int count = countMineAround(board, row, column);
            if (count > 0) {
                board[row][column] = (char) ('0' + count);
                return board;
            } else {
                board[row][column] = 'B';
            }


            Deque<int[]> stack = new ArrayDeque<>();
            fillTheStack(stack, m, n, row, column);
            while (!stack.isEmpty()) {
                int[] cur = stack.pop();
                if (board[cur[0]][cur[1]] == 'E') {
                    int curCount = countMineAround(board, cur[0], cur[1]);
                    if (curCount > 0) {
                        board[cur[0]][cur[1]] = (char) ('0' + curCount);
                    } else {
                        board[cur[0]][cur[1]] = 'B';
                        fillTheStack(stack, m, n, cur[0], cur[1]);
                    }
                }
            }
            return board;
        }

        private int hash(int[] index) {
            return index[0] * 100 + index[1];
        }

        /**
         * if the square we revealed is 'B', all of its adjacent unrevealed squares should be revealed;
         */
        private void fillTheStack(Deque<int[]> stack, int m, int n, int row, int column) {
            if (row - 1 >= 0) {
                stack.push(new int[]{row - 1, column});
                if (column + 1 < n) {
                    stack.push(new int[]{row - 1, column + 1});
                }
                if (column - 1 >= 0) {
                    stack.push(new int[]{row - 1, column - 1});
                }
            }
            if (column + 1 < n) {
                stack.push(new int[]{row, column + 1});
            }
            if (column - 1 >= 0) {
                stack.push(new int[]{row, column - 1});
            }
            if (row + 1 < m) {
                stack.push(new int[]{row + 1, column});
                if (column + 1 < n) {
                    stack.push(new int[]{row + 1, column + 1});
                }
                if (column - 1 >= 0) {
                    stack.push(new int[]{row + 1, column - 1});
                }
            }
        }

        /**
         * count the number of mines that are adjacent the revealed square
         */
        private int countMineAround(char[][] board, int row, int column) {
            int count = 0;
            int m = board.length;
            int n = board[0].length;
            if (row - 1 >= 0) {
                count += board[row - 1][column] == 'M' ? 1 : 0;
                if (column + 1 < n) {
                    count += board[row - 1][column + 1] == 'M' ? 1 : 0;
                }
                if (column - 1 >= 0) {
                    count += board[row - 1][column - 1] == 'M' ? 1 : 0;
                }
            }
            if (column + 1 < n) {
                count += board[row][column + 1] == 'M' ? 1 : 0;
            }
            if (column - 1 >= 0) {
                count += board[row][column - 1] == 'M' ? 1 : 0;
            }
            if (row + 1 < m) {
                count += board[row + 1][column] == 'M' ? 1 : 0;
                if (column + 1 < n) {
                    count += board[row + 1][column + 1] == 'M' ? 1 : 0;
                }
                if (column - 1 >= 0) {
                    count += board[row + 1][column - 1] == 'M' ? 1 : 0;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}