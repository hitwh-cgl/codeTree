package cn;

//汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
//
// 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽
//油。 
//
// 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。 
//
// 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。 
//
// 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。 
//
// 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。 
//
// 
//
// 示例 1： 
//
// 输入：target = 1, startFuel = 1, stations = []
//输出：0
//解释：我们可以在不加油的情况下到达目的地。
// 
//
// 示例 2： 
//
// 输入：target = 100, startFuel = 1, stations = [[10,100]]
//输出：-1
//解释：我们无法抵达目的地，甚至无法到达第一个加油站。
// 
//
// 示例 3： 
//
// 输入：target = 100, startFuel = 10, stations = [[10,60},{20,30},{30,30},{60,40]]
//
//输出：2
//解释：
//我们出发时有 10 升燃料。
//我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
//然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
//并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
//我们沿途在1两个加油站停靠，所以返回 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target, startFuel, stations[i][1] <= 10^9 
// 0 <= stations.length <= 500 
// 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < 
//target 
// 
// Related Topics 贪心 数组 动态规划 堆（优先队列） 
// 👍 233 👎 0


import java.util.PriorityQueue;
import java.util.TreeSet;

public class MinimumNumberOfRefuelingStops_871 {
    public static void main(String[] args) {
        int[][] stations = new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        Solution solution = new Solution();
        solution.minRefuelStops(100, 10, stations);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int times = 0;
            int curFuel = startFuel;
            int pre = 0;
            for (int i = 0; i < stations.length; i++) {
                int[] cur = stations[i];
                curFuel -= cur[0] - pre;
                pre = cur[0];
                while (curFuel < 0 && !queue.isEmpty()) {
                    curFuel += queue.poll();
                    times++;
                }
                if (curFuel < 0) {
                    return -1;
                }
                queue.offer(cur[1]);
            }
            while (curFuel + pre < target && !queue.isEmpty()) {
                curFuel += queue.poll();
                times++;
            }

            if (curFuel + pre >= target) {
                return times;
            } else {
                return -1;
            }
        }

        public int dp(int target, int startFuel, int[][] stations) {
            int n = stations.length;
            // dp[i] 表示经过i次加油后能行驶的最大里程数;
            long[] dp = new long[n + 1];
            dp[0] = startFuel;
            // 遍历每个加油站
            for (int i = 0; i < n; i++) {
                int[] cur = stations[i];
                for (int j = i + 1; j > 0; j--) {
                    if (dp[j - 1] >= cur[0]) {
                        dp[j] = Math.max(dp[j], dp[j - 1] + cur[1]);
                    } else {
                        // 如果不使用long会出现溢出问题，提交不通过；题解给出int[]数组会存在问题；
                        break;
                    }
                }
            }

            for (int i = 0; i <= n; i++) {
                if (dp[i] >= target) {
                    return i;
                }
            }
            return -1;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}