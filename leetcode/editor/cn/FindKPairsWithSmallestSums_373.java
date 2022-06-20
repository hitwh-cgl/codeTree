package cn;

//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 105 
// -109 <= nums1[i], nums2[i] <= 109 
// nums1 和 nums2 均为升序排列 
// 1 <= k <= 1000 
// 
// Related Topics 数组 堆（优先队列） 
// 👍 412 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums_373 {

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 7, 11};
        int[] num2 = new int[]{2, 4, 6};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.kSmallestPairs(num1, num2, 3);
        System.out.println(lists);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            PriorityQueue<int[]> queue = new PriorityQueue<>(k,
                    (i1, i2) -> nums1[i1[0]] + nums2[i1[1]] - nums1[i2[0]] - nums2[i2[1]]);
            // if we don't insert all i,0 at first, we need to offer i+1,j and i,j+1, which need to filter duplicate array;
            for (int i = 0; i < Math.min(k, nums1.length); i++) {
                queue.offer(new int[]{i, 0});
            }
            List<List<Integer>> result = new ArrayList<>();
            // if there is no enough element, return all;
            while (k-- > 0 && !queue.isEmpty()) {
                int[] cur = queue.poll();
                List<Integer> insert = new ArrayList<>(2);
                insert.add(nums1[cur[0]]);
                insert.add(nums2[cur[1]]);
                result.add(insert);
                if (cur[1] < nums2.length - 1) {
                    queue.offer(new int[]{cur[0], cur[1] + 1});
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}