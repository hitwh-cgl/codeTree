package cn;

//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能
//折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。 
//
// 如果你能使这个正方形，则返回 true ，否则返回 false 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
// 
//
// 示例 2: 
//
// 
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= matchsticks.length <= 15 
// 1 <= matchsticks[i] <= 108 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 392 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class MatchsticksToSquare_473 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6};
        boolean success = solution.makesquare(array);
        System.out.println(success);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean makesquare(int[] matchsticks) {
            if (matchsticks.length < 4) {
                return false;
            }
            int sumLength = 0;
            for (int length : matchsticks) {
                sumLength += length;
            }
            if (sumLength % 4 != 0) {
                return false;
            }
            int targetLength = sumLength / 4;
            // 排序以后会快很多，因为会先塞满每个边，很快发现没有办法满足条件；
            Arrays.sort(matchsticks);
            for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
                if (matchsticks[i] > targetLength || matchsticks[j] > targetLength) {
                    return false;
                }
                int temp = matchsticks[i];
                matchsticks[i] = matchsticks[j];
                matchsticks[j] = temp;
            }

            int[] value = new int[4];

            return search(matchsticks, 0, value, targetLength);
        }

        private boolean search(int[] matchsticks, int index, int[] values, int target) {
            if (index == matchsticks.length - 1) {
                for (int i = 0; i < 4; i++) {
                    if (values[i] + matchsticks[index] == target) {
                        values[i] += matchsticks[index];
                        return success(values, target);
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (values[i] + matchsticks[index] <= target) {
                        values[i] += matchsticks[index];
                        boolean success = search(matchsticks, index + 1, values, target);
                        if (success) {
                            return true;
                        }
                        values[i] -= matchsticks[index];
                    }
                }
            }
            return false;
        }

        private boolean success(int[] value, int target) {
            return value[0] == target && value[1] == target && value[2] == target && value[3] == target;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}