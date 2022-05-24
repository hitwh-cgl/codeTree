package cn;

//给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//提示：
//
// 
// n == nums.length 
// 1 <= n <= 5 * 104 
// -109 <= nums[i] <= 109 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// Related Topics 数组 哈希表 分治 计数 排序 
// 👍 1445 👎 0


/**
 * 很多解法可以有空都写写看：
 * 1.hash表计数；
 * 2排序
 * 3.随机取数后验证；
 * 4.分治算法
 *
 * @author liuchenguang002
 */
public class MajorityElement_169 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 投票算法
         */
        public int majorityElement(int[] nums) {
            // [2,2,1,1,1,2,2]
            int count = 1;
            int cur = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == cur) {
                    count++;
                } else {
                    if (count == 0) {
                        count++;
                        cur = nums[i];
                    } else {
                        count--;
                    }
                }
            }
            return cur;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}