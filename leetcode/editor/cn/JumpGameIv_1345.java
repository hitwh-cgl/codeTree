package cn;

//给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。 
//
// 每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ： 
//
// 
// i + 1 需满足：i + 1 < arr.length 
// i - 1 需满足：i - 1 >= 0 
// j 需满足：arr[i] == arr[j] 且 i != j 
// 
//
// 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。 
//
// 注意：任何时候你都不能跳到数组外面。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
//输出：3
//解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
// 
//
// 示例 2： 
//
// 
//输入：arr = [7]
//输出：0
//解释：一开始就在最后一个元素处，所以你不需要跳跃。
// 
//
// 示例 3： 
//
// 
//输入：arr = [7,6,9,6,9,6,9,7]
//输出：1
//解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
// 
//
// 
//
// 提示： 
// 
//
// 
// 1 <= arr.length <= 5 * 104 
// -108 <= arr[i] <= 108 
// 
// Related Topics 广度优先搜索 数组 哈希表 
// 👍 196 👎 0


import java.util.*;

public class JumpGameIv_1345 {
    public static void main(String[] args) {
        int[] arr = {25, -28, -51, 61, -74, -51, -30, 58, 36, 68, -80, -64, 25, -30, -53, 36, -74, 61, -100, -30, -52};
        // 0   1    2   3   4    3    4    5   6   7    8    9    1   2    3    4   5    4   5    5    6
        Solution solution = new Solution();
        int i = solution.minJumps(arr);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minJumps(int[] arr) {
            Set<Integer> visited = new HashSet<>();
            Map<Integer, List<Integer>> valMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                valMap.putIfAbsent(arr[i], new ArrayList<>());
                valMap.get(arr[i]).add(i);
            }
            Queue<int[]> queue = new ArrayDeque<>();
            visited.add(0);
            int[] init = {0, 0};
            queue.offer(init);
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int index = cur[0];
                int step = cur[1];
                if (index == arr.length - 1) {
                    return step;
                }
                if (valMap.containsKey(arr[index])) {
                    List<Integer> sameList = valMap.get(arr[index]);
                    for (Integer nextIndex : sameList) {
                        if (visited.add(nextIndex)) {
                            int[] next = {nextIndex, step + 1};
                            queue.offer(next);
                        }
                    }
                    // remove useless record
                    valMap.remove(arr[index]);
                }
                if (index + 1 < arr.length && visited.add(index + 1)) {
                    int[] next = {index + 1, step + 1};
                    queue.offer(next);
                }
                if (index - 1 >= 0 && visited.add(index - 1)) {
                    int[] next = {index - 1, step + 1};
                    queue.offer(next);
                }
            }
            return arr.length - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}