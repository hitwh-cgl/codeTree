package cn;

//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 哈希表 
// 👍 1259 👎 0


import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class LongestConsecutiveSequence_128 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int longestConsecutive(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int num : nums) {
                treeSet.add(num);
            }

            int max = 1, cur = 1, copy;
            Iterator<Integer> iterator = treeSet.iterator();
            copy = iterator.next();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next == copy + 1) {
                    cur++;
                    max = Math.max(cur, max);
                    copy = next;
                } else {
                    copy = next;
                    cur = 1;
                }
            }
            return max;
        }

        public int set(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            HashSet<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }

            int max = 1;
            for (Integer cur : numSet) {
                // 这一行判断比较精妙，只从连续数组头开始判断，避免很多无效查询
                if (!numSet.contains(cur - 1)) {
                    int length = 1;
                    while (numSet.contains(cur + 1)) {
                        cur += 1;
                        length += 1;
                    }
                    max = Math.max(max, length);
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}