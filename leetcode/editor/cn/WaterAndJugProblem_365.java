package cn;

//有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 targetCap
//acity 升。 
//
// 如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。 
//
// 你可以： 
//
// 
// 装满任意一个水壶 
// 清空任意一个水壶 
// 从一个水壶向另外一个水壶倒水，直到装满或者倒空 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
//输出: true
//解释：来自著名的 "Die Hard" 
//
// 示例 2: 
//
// 
//输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
//输出: false
// 
//
// 示例 3: 
//
// 
//输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
//输出: true
// 
//
// 
//
// 提示: 
//
// 
// 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106 
// 
// Related Topics 深度优先搜索 广度优先搜索 数学 
// 👍 377 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class WaterAndJugProblem_365 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
            Set<Long> seen = new HashSet<>();
            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{0, 0});
            while (!stack.isEmpty()) {
                int[] cur = stack.pop();
                long hash = hash(cur);
                if (seen.add(hash)) {
                    int remindX = cur[0];
                    int remindY = cur[1];
                    if (remindX == targetCapacity || remindY == targetCapacity
                            || remindX + remindY == targetCapacity) {
                        return true;
                    }

                    stack.push(new int[]{jug1Capacity, remindY});
                    stack.push(new int[]{0, remindY});
                    stack.push(new int[]{remindX, jug2Capacity});
                    stack.push(new int[]{remindX, 0});
                    // 左边往右边灌水
                    if (remindX > jug2Capacity - remindY) {
                        stack.push(new int[]{remindX - (jug2Capacity - remindY), jug2Capacity});
                    } else {
                        stack.push(new int[]{0, remindY + remindX});
                    }
                    // 右边往左边灌水
                    if (remindY > jug1Capacity - remindX) {
                        stack.push(new int[]{jug1Capacity, remindY - (jug1Capacity - remindX)});
                    } else {
                        stack.push(new int[]{remindX + remindY, 0});
                    }
                }
            }
            return false;
        }

        /**
         * 注意溢出
         */
        public long hash(int[] state) {
            return (long) state[0] * 1000000 + state[1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}