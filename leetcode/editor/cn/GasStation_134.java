package cn;

//在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。 
//
// 
//
// 示例 1: 
//
// 
//输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//输出: 3
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 
//输入: gas = [2,3,4], cost = [3,4,3]
//输出: -1
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
//
// 
//
// 提示: 
//
// 
// gas.length == n 
// cost.length == n 
// 1 <= n <= 105 
// 0 <= gas[i], cost[i] <= 104 
// 
// Related Topics 贪心 数组 
// 👍 938 👎 0


/**
 * 如何让当前出发点富余汽油最多？
 * 1.如果从i点出发不行，过程中途径的每个点都不行
 * 2.如果i-1点位置汽油大于消耗，i-1点比i点更有希望
 *
 * @author 17862
 */
public class GasStation_134 {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        Solution solution = new Solution();
        solution.canCompleteCircuit(gas, cost);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int i = 0; i < n; i++) {
                int startIndex = i;
                do {
                    int index = (startIndex + n - 1) % n;
                    if (gas[index] - cost[index] >= 0) {
                        startIndex = index;
                        if (startIndex - i + 1 >= n || i - startIndex + 1 >= n) {
                            break;
                        }
                    } else {
                        break;
                    }
                } while (true);
                int step = maxStep(gas, cost, startIndex);
                if (step == n) {
                    return startIndex;
                } else {
                    i = i + step;
                }
            }
            return -1;
        }

        private int maxStep(int[] gas, int[] cost, int start) {
            int sum = 0;
            int step = 0;
            int n = gas.length;
            do {
                int curIndex = (start + step) % n;
                sum = gas[curIndex] + sum - cost[curIndex];
                if (sum >= 0) {
                    step++;
                    if (step == n) {
                        return step;
                    }
                } else {
                    return step;
                }
            } while (true);
        }

        /**
         * 旧版本，慢一点但是要简单很多；
         */
        public int preVersion(int[] gas, int[] cost) {
            int count = gas.length;
            for (int i = 0; i < count; i++) {
                if (gas[i] >= cost[i]) {
                    int sum = 0;
                    boolean success = true;
                    for (int j = 0; j < count; j++) {
                        int index = (i + j) % count;
                        sum += gas[index];
                        sum -= cost[index];
                        if (sum < 0) {
                            success = false;
                            i += j;
                            break;
                        }
                    }
                    if (success) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}