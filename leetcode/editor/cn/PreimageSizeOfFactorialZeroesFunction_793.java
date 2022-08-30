package cn;

//f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
//
// 
// 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。 
// 
//
// 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 0
//输出：5
//解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
// 
//
// 示例 2： 
//
// 
//输入：k = 5
//输出：0
//解释：没有匹配到这样的 x!，符合 k = 5 的条件。 
//
// 示例 3: 
//
// 
//输入: k = 3
//输出: 5
// 
//
// 
//
// 提示: 
//
// 
// 0 <= k <= 109 
// 
// Related Topics 数学 二分查找 
// 👍 174 👎 0

/**
 * 有几个有意思的点
 * 1.能不能想到用二分法，二分法的开闭区间
 * 2.能不能想到答案只会是0或5
 * 3.能不能想到int会溢出，需要转换成long
*/
public class PreimageSizeOfFactorialZeroesFunction_793 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.preimageSizeFZF(3);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int preimageSizeFZF(int k) {
            if (k == 0) {
                return 5;
            } else {
                if (getX(k) == -1) {
                    return 0;
                } else {
                    return 5;
                }
            }
        }

        private long getX(int k) {
            long right = 5 * (long) k;
            long left = 5;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long fx = fx(mid);
                if (fx > k) {
                    right = mid - 1;
                } else if (fx == k) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

        private long fx(long x) {
            int count = 0;
            while (x >= 5) {
                x /= 5;
                count += x;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}