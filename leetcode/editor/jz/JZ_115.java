package jz;

import java.util.*;

public class JZ_115 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 3, 4, 2};
        int[][] sequences = new int[][]{{1, 4}, {1, 3, 2}, {3, 4, 2}};
        boolean result = solution.sequenceReconstruction(nums, sequences);
        System.out.println(result);
    }

    static class Solution {
        public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
            // 后续节点值 和 对应的入度
            Map<Integer, Integer> nextCount = new HashMap<>(nums.length);
            // 前序节点值 和 对应后续节点链表
            Map<Integer, List<Integer>> nextList = new HashMap<>(nums.length);
            for (int[] out : sequences) {
                for (int i = 0; i + 1 < out.length; i++) {
                    int pre = out[i];
                    int next = out[i + 1];
                    nextCount.merge(next, 1, Integer::sum);
                    List<Integer> list = nextList.get(pre);
                    if (list == null) {
                        list = new ArrayList<>();
                        list.add(next);
                        nextList.put(pre, list);
                    } else {
                        list.add(next);
                    }
                }
            }

            Deque<Integer> queue = new ArrayDeque<>();
            for (int num : nums) {
                if (!nextCount.containsKey(num)) {
                    queue.add(num);
                }
            }

            while (!queue.isEmpty()) {
                if (queue.size() > 1) {
                    return false;
                } else {
                    int pre = queue.pop();
                    List<Integer> list = nextList.remove(pre);
                    if (list != null) {
                        for (int next : list) {
                            Integer count = nextCount.get(next);
                            if (count == 1) {
                                nextCount.remove(next);
                                queue.add(next);
                            } else {
                                nextCount.put(next, count - 1);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
