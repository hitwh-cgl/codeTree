package cn;

//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。 
//
// 
//
// 示例 1: 
//
// 
//输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
//输出: 1
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: intervals = [ [1,2], [1,2], [1,2] ]
//输出: 2
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: intervals = [ [1,2], [2,3] ]
//输出: 0
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= intervals.length <= 105 
// intervals[i].length == 2 
// -5 * 104 <= starti < endi <= 5 * 104 
// 
// Related Topics 贪心 数组 动态规划 排序 
// 👍 792 👎 0


import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals_435 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 优化思路
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 1) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
            int edge = Integer.MIN_VALUE;
            int count = 0;
            for (int[] cur : intervals) {
                if (edge <= cur[0]) {
                    edge = cur[1];
                } else {
                    count++;
                }
            }
            return count;
        }

        public int version2(int[][] intervals) {
            if (intervals.length == 1) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
            int count = 0;
            int right = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int[] cur = intervals[i];
                if (cur[1] == right) {
                    count++;
                } else {
                    // cur[1] > right
                    if (cur[0] >= right) {
                        right = cur[1];
                    } else {
                        count++;
                    }
                }
            }
            return count;
        }


        public int version1(int[][] intervals) {
            if (intervals.length == 1) {
                return 0;
            }
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            });
            int count = 0;
            int minLeft = intervals[0][0];
            int minRight = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int[] cur = intervals[i];
                if (cur[0] == minLeft) {
                    count++;
                } else {
                    // minLeft < cur[0]
                    if (cur[0] >= minRight) {
                        minLeft = cur[0];
                        minRight = cur[1];
                    } else if (cur[1] < minRight) {
                        minLeft = cur[0];
                        minRight = cur[1];
                        count++;
                    } else {
                        count++;
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}