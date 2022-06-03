package cn;

//给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 5
//输出: 2
//解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。 
//
// 示例 2: 
//
// 
//输入: n = 9
//输出: 3
//解释: 9 = 4 + 5 = 2 + 3 + 4 
//
// 示例 3: 
//
// 
//输入: n = 15
//输出: 4
//解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 109 
// 
// Related Topics 数学 枚举 
// 👍 153 👎 0


public class ConsecutiveNumbersSum_829 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int consecutiveNumbersSum(int n) {
            //   (x+x+i-1)*i/2=n
            //=> 2ix = 2n-i*(i-1)
            //=> x = [2n-i*(i-1)]/2i
            int count = 1;
            for (int i = 2; i * (i - 1) < 2 * n; i++) {
                if ((2 * n - i * (i - 1)) % (2 * i) == 0) {
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}