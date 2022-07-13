package cn;

//给定一个整数数组 asteroids，表示在同一行的行星。
//
// 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。 
//
// 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞
//。 
//
// 
//
// 示例 1： 
//
// 
//输入：asteroids = [5,10,-5]
//输出：[5,10]
//解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。 
//
// 示例 2： 
//
// 
//输入：asteroids = [8,-8]
//输出：[]
//解释：8 和 -8 碰撞后，两者都发生爆炸。 
//
// 示例 3： 
//
// 
//输入：asteroids = [10,2,-5]
//输出：[10]
//解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。 
//
// 
//
// 提示： 
//
// 
// 2 <= asteroids.length <= 104 
// -1000 <= asteroids[i] <= 1000 
// asteroids[i] != 0 
// 
// Related Topics 栈 数组 
// 👍 270 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class AsteroidCollision_735 {
    public static void main(String[] args) {
        int[] asteroids = new int[]{10};
        int[] expect = new int[]{10};

        Solution solution = new Solution();
        int[] result = solution.asteroidCollision(asteroids);
        System.out.println(result.length == expect.length);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i] == expect[i]);
        }
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            // -10 -5
            // 10 5
            // -10 10
            // -5 5 8 -10
            // -5 20 -10
            // 10
            // stack
            // O(n) n=nums.length
            Deque<Integer> deque = new ArrayDeque<>();
            int index = 0;
            while (index < asteroids.length) {
                int cur = asteroids[index];
                if (cur > 0) {
                    deque.push(cur);
                    index++;
                    continue;
                }
                // 为空或者没有正数直接入栈
                if (deque.isEmpty() || deque.peek() < 0) {
                    deque.push(cur);
                    index++;
                    continue;
                }
                // 有正数比较大小
                int pre = deque.peek();
                if (pre + cur > 0) {
                    index++;
                } else if (pre + cur == 0) {
                    deque.pop();
                    index++;
                } else {
                    deque.pop();
                }
            }

            int[] result = new int[deque.size()];
            index = deque.size() - 1;
            while (!deque.isEmpty()) {
                result[index] = deque.pop();
                index--;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}