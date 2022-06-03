package cn;

//给你一个整数 num ，请你返回三个连续的整数，它们的 和 为 num 。如果 num 无法被表示成三个连续整数的和，请你返回一个 空 数组。 
//
// 
//
// 示例 1： 
//
// 输入：num = 33
//输出：[10,11,12]
//解释：33 可以表示为 10 + 11 + 12 = 33 。
//10, 11, 12 是 3 个连续整数，所以返回 [10, 11, 12] 。
// 
//
// 示例 2： 
//
// 输入：num = 4
//输出：[]
//解释：没有办法将 4 表示成 3 个连续整数的和。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= num <= 1015 
// 
// Related Topics 数学 模拟 
// 👍 6 👎 0


public class FindThreeConsecutiveIntegersThatSumToAGivenNumber_2177 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long[] sumOfThree(long num) {
            // (x+x+2)*3=2*num
            // 6*x=2*num-6;
            long start = (num * 2 - 6) / 6;
            boolean success = (num * 2 - 6) % 6 == 0;
            if (success) {
                return new long[]{start, start + 1, start + 2};
            }
            return new long[]{};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}