package cn;

//一个整数区间 [a, b] ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
//
// 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。 
//
// 输出这个最小集合S的大小。 
//
// 示例 1: 
//
// 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
//输出: 3
//解释:
//考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
//且这是S最小的情况，故我们输出3。
// 
//
// 示例 2: 
//
// 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
//输出: 5
//解释:
//最小的集合S = {1, 2, 3, 4, 5}.
// 
//
// 注意: 
//
// 
// intervals 的长度范围为[1, 3000]。 
// intervals[i] 长度为 2，分别代表左、右边界。 
// intervals[i][j] 的值是 [0, 10^8]范围内的整数。 
// 
// Related Topics 贪心 数组 排序 
// 👍 153 👎 0


import java.util.Arrays;

/**
 * todo 贪心算法
 */
public class SetIntersectionSizeAtLeastTwo_757 {
    public static void main(String[] args) {
        // 01 23
        // 01 12
        // 02 13
        int[][] intervals = new int[][]{
                {0, 1},
                {2, 3},
                {0, 2},
                {1, 2},
                {1, 3}
        };
        Solution solution = new Solution();
        int sizeTwo = solution.intersectionSizeTwo(intervals);
        System.out.println(sizeTwo);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int prefix = 0;

        public int intersectionSizeTwo(int[][] intervals) {
            if (intervals.length == 1) {
                return 2;
            }

            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            });

            // 0 3 0 4 1 2
            int[] cur = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                cur = merge(cur, intervals[i]);
            }
            return prefix + 2;
        }

        private int[] merge(int[] left, int[] right) {
            // 01 12
            // 01 23
            // 02 13
            if (left[1] == right[0]) {
                prefix += 1;
                return new int[]{right[1]};
            } else if (left[1] > right[0]) {
                return new int[]{left[1] - 1, left[1]};
            } else {
                prefix += 2;
                return right;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}