package cn;

//给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。 
//
// 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。 
//
// 返回 你能获得的 最大 利润 。 
//
// 
//
// 示例 1： 
//
// 
//输入：prices = [7,1,5,3,6,4]
//输出：7
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//     总利润为 4 + 3 = 7 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     总利润为 4 。 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 104 
// 0 <= prices[i] <= 104 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1713 👎 0


/**
 * 一开始我是这么写的：curNotHold=Math.max(notHold,hold+prices[i]); curHold = Math.max(hold, notHold-prices[i])
 * 后来我再思考，需不需要中间变量来存储过程值，我的思考结果是不需要;
 * 1.仔细观察，股票只有上涨不持有的最大利润才会变化，此时带入变化后的不持有利润，持有最大利润不会变化；
 * 2.再仔细观察，股票只有下跌持有的最大利润才会变化，此时带入变化后的持有利润，不持有最大利润不会变化；
 *
 * @author liuchenguang002
 */
public class BestTimeToBuyAndSellStockIi_122 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4};
        solution.maxProfit(prices);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            int hold = -prices[0];
            int notHold = 0;
            for (int i = 1; i < prices.length; i++) {
                notHold = Math.max(notHold, hold + prices[i]);
                hold = Math.max(hold, notHold - prices[i]);
            }
            return notHold;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}