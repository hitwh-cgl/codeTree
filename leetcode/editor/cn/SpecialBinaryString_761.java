package cn;

//特殊的二进制序列是具有以下两个性质的二进制序列：
//
// 
// 0 的数量与 1 的数量相等。 
// 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。 
// 
//
// 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一
//个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。) 
//
// 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？ 
//
// 示例 1: 
//
// 
//输入: S = "11011000"
//输出: "11100100"
//解释:
//将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
//这是在进行若干次操作后按字典序排列最大的结果。
// 
//
// 说明: 
//
// 
// S 的长度不超过 50。 
// S 保证为一个满足上述定义的特殊 的二进制序列。 
// 
// Related Topics 递归 字符串 
// 👍 126 👎 0


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1.虽然lambda表达式的函数式写法很舒服，但是提交结果证明 stream.sort排序没有list.sort排序快；
 * 2.构造生成字符串时，String.join() 比 steam.joining快，但是都不如stringBuilder直接构建；
 * 3.本题思路在于分割字符串时往往需要剥开外层的10，但是外层的10已经是最优解，而且每个特殊二进制序列最外层都是10，因此可以直接剥开；
 *
 * @author 17862
 */
public class SpecialBinaryString_761 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.makeLargestSpecial("111111000110000100");
        System.out.println(result);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String makeLargestSpecial(String s) {
            if (s.length() <= 2) {
                return s;
            }
            List<String> stringList = new ArrayList<>();
            int count = 0;
            int left = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    count++;
                } else {
                    count--;
                    if (count == 0) {
                        String source = s.substring(left + 1, i);
                        left = i + 1;
                        stringList.add("1" + makeLargestSpecial(source) + "0");
                    }
                }
            }

            stringList.sort(Comparator.reverseOrder());
            StringBuilder builder = new StringBuilder();
            for (String sub : stringList) {
                builder.append(sub);
            }
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}