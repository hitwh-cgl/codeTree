package cn;

//有 n 个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID 。
//
// 给定一个整数数组 groupSizes ，其中 groupSizes[i] 是第 i 个人所在的组的大小。例如，如果 groupSizes[1] = 3 
//，则第 1 个人必须位于大小为 3 的组中。 
//
// 返回一个组列表，使每个人 i 都在一个大小为 groupSizes[i] 的组中。 
//
// 每个人应该 恰好只 出现在 一个组 中，并且每个人必须在一个组中。如果有多个答案，返回其中 任何 一个。可以 保证 给定输入 至少有一个 有效的解。 
//
// 
//
// 示例 1： 
//
// 
//输入：groupSizes = [3,3,3,3,3,1,3]
//输出：[[5],[0,1,2],[3,4,6]]
//解释：
//第一组是 [5]，大小为 1，groupSizes[5] = 1。
//第二组是 [0,1,2]，大小为 3，groupSizes[0] = groupSizes[1] = groupSizes[2] = 3。
//第三组是 [3,4,6]，大小为 3，groupSizes[3] = groupSizes[4] = groupSizes[6] = 3。 
//其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
// 
//
// 示例 2： 
//
// 
//输入：groupSizes = [2,1,3,3,3,2]
//输出：[[1],[0,5],[2,3,4]]
// 
//
// 
//
// 提示： 
//
// 
// groupSizes.length == n 
// 1 <= n <= 500 
// 1 <= groupSizes[i] <= n 
// 
// Related Topics 数组 哈希表 
// 👍 97 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo_1282 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.groupThePeople(new int[]{3, 3, 3, 3, 3, 1, 3});
        System.out.println(lists.size());
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 参考别人的写法，时间复杂度也是O(n),但是容器操作更简单和方便，性能更好
         */
        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < groupSizes.length; i++) {
                if (groupSizes[i] != 0) {
                    int length = groupSizes[i];
                    List<Integer> cur = new ArrayList<>();
                    cur.add(i);
                    length--;
                    for (int j = i + 1; j < groupSizes.length && length > 0; j++) {
                        if (groupSizes[j] == groupSizes[i]) {
                            cur.add(j);
                            groupSizes[j] = 0;
                            length--;
                        }
                    }
                    result.add(cur);
                }
            }
            return result;
        }

        /**
         * 使用哈希表分组后
         */
        public List<List<Integer>> version1(int[] groupSizes) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < groupSizes.length; i++) {
                int size = groupSizes[i];
                List<Integer> list = map.get(size);
                if (list != null) {
                    list.add(i);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(i);
                    map.put(size, newList);
                }
            }

            List<List<Integer>> result = new ArrayList<>();
            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                List<Integer> cur = new ArrayList<>();
                int size = entry.getKey();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if (i % size == 0) {
                        cur = new ArrayList<>();
                        result.add(cur);
                    }
                    cur.add(entry.getValue().get(i));
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}