package cn;

//给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//解释：[4,9] 也是可通过的
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 1000 
// 
// Related Topics 数组 哈希表 双指针 二分查找 排序 
// 👍 504 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArrays_349 {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int l1 = nums1.length, l2 = nums2.length;
            List<Integer> list = new ArrayList<>();
            for (int p1 = 0, p2 = 0; p1 < l1 && p2 < l2; ) {
                if (nums1[p1] == nums2[p2]) {
                    list.add(nums1[p1]);
                    while (p1 + 1 < l1 && nums1[p1] == nums1[p1 + 1]) {
                        p1++;
                    }
                    p1++;
                    while (p2 + 1 < l2 && nums2[p2] == nums2[p2 + 1]) {
                        p2++;
                    }
                    p2++;
                } else if (nums1[p1] < nums2[p2]) {
                    p1++;
                } else if (nums1[p1] > nums2[p2]) {
                    p2++;
                }
            }
            return list.stream().mapToInt(e -> e).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}