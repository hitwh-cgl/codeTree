package cn;

//病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
//
// 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而 isInfecte
//d[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。 
//
// 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连
//续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。 
//
// 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0
//,0,0,0,0,0]]
//输出: 10
//解释:一共有两块被病毒感染的区域。
//在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
//
//第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
//
// 
//
// 示例 2： 
//
// 
//
// 
//输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
//输出: 4
//解释: 虽然只保存了一个小区域，但却有四面墙。
//注意，防火墙只建立在两个不同区域的共享边界上。
// 
//
// 示例 3: 
//
// 
//输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
//
//输出: 13
//解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
// 
//
// 
//
// 提示: 
//
// 
// m == isInfected.length 
// n == isInfected[i].length 
// 1 <= m, n <= 50 
// isInfected[i][j] is either 0 or 1 
// 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块 
// 
//
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 模拟 
// 👍 110 👎 0


import utils.ArrayUtils;

import java.util.*;

/**
 * 1.病毒传播感染的方块和隔离这片病毒需要的防火墙数量不一定相等，例如中间有个洞的；
 * 2.病毒传播以后可能会把两片病毒区域连到一起；
 *
 * @author 17862
 */
public class ContainVirus_749 {
    public static void main(String[] args) {
        String source =
                "[" +
                        "[0,0,0,0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0,1,0,0]," +
                        "[1,0,0,0,0,0,0,0,0,0]," +
                        "[0,0,1,0,0,0,1,0,0,0]," +
                        "[0,0,0,0,0,0,1,0,0,0]," +
                        "[0,0,0,0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0,0,1,0]," +
                        "[0,0,0,0,1,0,1,0,0,0]," +
                        "[0,0,0,0,0,0,0,0,0,0]]";
        int[][] isInfected = ArrayUtils.parse(source);
        Solution solution = new Solution();
        System.out.println(solution.containVirus(isInfected));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static
    class Solution {
        private int m;
        private int n;

        public int containVirus(int[][] isInfected) {
            m = isInfected.length;
            n = isInfected[0].length;
            int totalWall = 0;
            int maxSpread = -1;
            int maxSpreadIndex = -1;
            Map<Integer, Set<Integer>> mapSpread = new HashMap<>();
            Map<Integer, Integer> mapWall = new HashMap<>();

            boolean[][] visit = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1) {
                        searchWallAndComputeSpread(isInfected, i, j, visit, mapSpread, mapWall);
                        if (mapSpread.get(hashIndex(i, j)).size() > maxSpread) {
                            maxSpread = mapSpread.get(hashIndex(i, j)).size();
                            maxSpreadIndex = hashIndex(i, j);
                        }
                    }
                }
            }
            if (maxSpread == -1 || maxSpread == 0) {
                return 0;
            }

            int[] block = hashReverse(maxSpreadIndex);
            totalWall += mapWall.get(maxSpreadIndex);
            block(isInfected, block[0], block[1]);
            mapSpread.remove(maxSpreadIndex);
            for (Set<Integer> set : mapSpread.values()) {
                for (int index : set) {
                    int[] cur = hashReverse(index);
                    isInfected[cur[0]][cur[1]] = 1;
                }
            }
            return totalWall + containVirus(isInfected);
        }

        private void block(int[][] isInfected, int x, int y) {
            Deque<int[]> deque = new ArrayDeque<>();
            deque.push(new int[]{x, y});
            while (!deque.isEmpty()) {
                int[] cur = deque.pop();
                x = cur[0];
                y = cur[1];
                isInfected[x][y] = 2;

                if (x + 1 < m) {
                    if (isInfected[x + 1][y] == 1) {
                        deque.push(new int[]{x + 1, y});
                    }
                }
                if (x - 1 >= 0) {
                    if (isInfected[x - 1][y] == 1) {
                        deque.push(new int[]{x - 1, y});
                    }
                }
                if (y + 1 < n) {
                    if (isInfected[x][y + 1] == 1) {
                        deque.push(new int[]{x, y + 1});
                    }
                }
                if (y - 1 >= 0) {
                    if (isInfected[x][y - 1] == 1) {
                        deque.push(new int[]{x, y - 1});
                    }
                }
            }
        }

        private void searchWallAndComputeSpread(int[][] isInfected, int x, int y,
                                                boolean[][] visit,
                                                Map<Integer, Set<Integer>> mapSpread,
                                                Map<Integer, Integer> mapWall) {
            int finalIndex = hashIndex(x, y);
            Set<Integer> spread = new HashSet<>();
            int wall = 0;

            Deque<int[]> deque = new ArrayDeque<>();
            deque.push(new int[]{x, y});
            while (!deque.isEmpty()) {
                int[] cur = deque.pop();
                x = cur[0];
                y = cur[1];
                if (visit[x][y]) {
                    continue;
                } else {
                    visit[x][y] = true;
                }

                if (x + 1 < m) {
                    if (isInfected[x + 1][y] == 0) {
                        spread.add(hashIndex(x + 1, y));
                        wall++;
                    } else if (isInfected[x + 1][y] == 1) {
                        deque.push(new int[]{x + 1, y});
                    }
                }
                if (x - 1 >= 0) {
                    if (isInfected[x - 1][y] == 0) {
                        spread.add(hashIndex(x - 1, y));
                        wall++;
                    } else if (isInfected[x - 1][y] == 1) {
                        deque.push(new int[]{x - 1, y});
                    }
                }
                if (y + 1 < n) {
                    if (isInfected[x][y + 1] == 0) {
                        spread.add(hashIndex(x, y + 1));
                        wall++;
                    } else if (isInfected[x][y + 1] == 1) {
                        deque.push(new int[]{x, y + 1});
                    }
                }
                if (y - 1 >= 0) {
                    if (isInfected[x][y - 1] == 0) {
                        spread.add(hashIndex(x, y - 1));
                        wall++;
                    } else if (isInfected[x][y - 1] == 1) {
                        deque.push(new int[]{x, y - 1});
                    }
                }
            }

            mapSpread.put(finalIndex, spread);
            mapWall.put(finalIndex, wall);
        }

        private int hashIndex(int x, int y) {
            return x * 100 + y;
        }

        private int[] hashReverse(int x) {
            return new int[]{x / 100, x % 100};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}