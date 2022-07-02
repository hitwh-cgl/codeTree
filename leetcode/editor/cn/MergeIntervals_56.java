package cn;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1534 👎 0


import java.util.*;

/**
 * 1.借鉴了一下之前我的日程安排题目里面的数组边界计数法，可以做就是需要考虑[0,0]这种区间，另外性能比较慢；
 *
 * @see MyCalendarIii_732
 */
public class MergeIntervals_56 {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Solution solution = new Solution();
        solution.merge(intervals);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            Set<Integer> set = new HashSet<>();
            int min = intervals[0][0];
            int max = intervals[0][1];
            for (int[] cur : intervals) {
                set.add(cur[0]);
                min = Math.min(min, cur[0]);
                max = Math.max(max, cur[1]);
            }
            int offset = min;
            int[] count = new int[max - min + 1];
            for (int[] cur : intervals) {
                count[cur[0] - offset]++;
                count[cur[1] - offset]--;
            }

            List<int[]> result = new ArrayList<>();
            int sum = -1;
            int start = 0;
            for (int i = 0; i < max - min + 1; i++) {
                if (sum == -1) {
                    if (count[i] == 0) {
                        if (set.contains(i + offset)) {
                            result.add(new int[]{i + offset, i + offset});
                        }
                    } else {
                        start = i + offset;
                        sum = count[i];
                    }
                } else {
                    sum += count[i];
                    if (sum == 0) {
                        int[] item = new int[]{start, i + offset};
                        result.add(item);
                        sum = -1;
                    }
                }
            }
            return result.toArray(new int[result.size()][]);
        }

        public int[][] mergeV2(int[][] intervals) {
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            for (int[] cur : intervals) {
                queue.offer(cur);
            }

            List<int[]> result = new ArrayList<>();
            int[] first = queue.poll();
            int start = first[0];
            int end = first[1];
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                if (cur[0] >= start && cur[0] <= end) {
                    end = Math.max(end, cur[1]);
                } else if (cur[0] > end) {
                    result.add(new int[]{start, end});
                    start = cur[0];
                    end = cur[1];
                }
            }
            return result.toArray(new int[result.size()][]);
        }


        public int[][] mergeV1(int[][] intervals) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int[] cur : intervals) {
                treeMap.put(cur[0], treeMap.getOrDefault(cur[0], 0) + 1);
                treeMap.put(cur[1], treeMap.getOrDefault(cur[1], 0) - 1);
            }

            List<int[]> result = new ArrayList<>();
            int sum = -1;
            int start = 0;
            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                if (sum == -1) {
                    if (entry.getValue() == 0) {
                        result.add(new int[]{entry.getKey(), entry.getKey()});
                    } else {
                        start = entry.getKey();
                        sum = entry.getValue();
                    }
                } else {
                    sum += entry.getValue();
                    if (sum == 0) {
                        int[] item = new int[]{start, entry.getKey()};
                        result.add(item);
                        sum = -1;
                    }
                }
            }

            int[][] answer = new int[result.size()][2];
            for (int i = 0; i < result.size(); i++) {
                answer[i] = result.get(i);
            }
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}