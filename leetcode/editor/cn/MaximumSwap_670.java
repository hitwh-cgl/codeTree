package cn;

//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
//
// 示例 1 : 
//
// 
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
// 
//
// 示例 2 : 
//
// 
//输入: 9973
//输出: 9973
//解释: 不需要交换。
// 
//
// 注意: 
//
// 
// 给定数字的范围是 [0, 108] 
// 
// Related Topics 贪心 数学 
// 👍 350 👎 0


import java.util.Arrays;

public class MaximumSwap_670 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumSwap(101));
        System.out.println(solution.maximumSwap(110));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumSwap(int num) {
            if (num < 10) {
                return num;
            }

            int[] count = new int[10];
            int copy = num;
            while (copy > 0) {
                count[copy % 10]++;
                copy /= 10;
            }

            String source = String.valueOf(num);
            int max = 9;
            for (int i = 0; i < source.length(); i++) {
                while (count[max] == 0) {
                    max--;
                }
                if (source.charAt(i) - '0' == max) {
                    count[max]--;
                } else {
                    for (int j = source.length() - 1; j >= 0; j--) {
                        if (source.charAt(j) - '0' == max) {
                            char[] value = Arrays.copyOf(source.toCharArray(), source.length());
                            value[j] = source.charAt(i);
                            value[i] = source.charAt(j);
                            return Integer.parseInt(new String(value));
                        }
                    }
                }
            }
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}