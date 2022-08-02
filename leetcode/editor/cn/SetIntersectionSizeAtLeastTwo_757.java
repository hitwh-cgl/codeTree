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

public class SetIntersectionSizeAtLeastTwo_757 {
    public static void main(String[] args) {
        // 01 23
        // 01 12
        // 02 13
        int[][] intervals = new int[][]{
                {2, 15}, {9, 17}, {0, 6}, {17, 25}, {0, 25}
        };
        Solution solution = new Solution();
        int sizeTwo = solution.intersectionSizeTwo(intervals);
        System.out.println(sizeTwo);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int count = 0;

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
            count = 2;
            int[] cur = new int[]{intervals[intervals.length - 1][0], intervals[intervals.length - 1][0] + 1};
            for (int i = intervals.length - 2; i >= 0; i--) {
                cur = merge(cur, intervals[i]);
            }
            return count;
        }

        private int[] merge(int[] cur, int[] merge) {
            // 02 12
            // 01 12
            // 01 23
            if (cur[0] > merge[1]) {
                count += 2;
                cur = new int[]{merge[0], merge[0] + 1};
            } else if (cur[0] == merge[1]) {
                count += 1;
                cur = new int[]{merge[0], cur[0]};
            } else {
                if (cur[1] <= merge[1]) {
                    // 14 23
                } else {
                    // 14 25
                    count++;
                    cur = new int[]{merge[0], cur[0]};
                }
            }
            return cur;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}