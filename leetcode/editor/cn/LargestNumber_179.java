package cn;

//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 贪心 字符串 排序 
// 👍 875 👎 0


import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 异常case: {0,0}
 */
public class LargestNumber_179 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String largestNumber(int[] nums) {
            StringBuilder sb = new StringBuilder();
            IntStream.of(nums).mapToObj(String::valueOf).sorted(
                    ((Comparator<String>) (s1, s2) -> compare(s1, s2)).reversed()).forEach(sb::append);
            String s = sb.toString();
            return s.charAt(0) == '0' ? "0" : s;
        }

        private int compare(String s1, String s2) {
            if (s1.equals(s2)) return 0;
            int i1 = 0, i2 = 0;
            for (; i1 < s1.length() && i2 < s2.length(); i1++, i2++) {
                if (s1.charAt(i1) > s2.charAt(i2)) {
                    return 1;
                } else if (s1.charAt(i1) < s2.charAt(i2)) {
                    return -1;
                }
            }
            if (i1 < s1.length()) {
                return compare(s1.substring(i1), s2);
            } else {
                return compare(s1, s2.substring(i2));
            }
        }

        public String largestNumber2(int[] nums) {
            String result = IntStream.of(nums).boxed().map(String::valueOf)
                    .sorted((x, y) -> (y + x).compareTo(x + y)).collect(Collectors.joining(""));
            return result.charAt(0) == '0' ? "0" : result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}