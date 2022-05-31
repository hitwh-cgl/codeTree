package cn;

//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 2268 👎 0


public class LongestCommonPrefix_14 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 拆解成小问题，类似动态规划的思路；一次遍历，两两计算结果；
        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            String prefix = strs[0];
            int count = strs.length;
            for (int i = 1; i < count; i++) {
                prefix = computePrefix(prefix, strs[i]);
                if ("".equals(prefix)) {
                    return "";
                }
            }
            return prefix;
        }

        private static String computePrefix(String left, String right) {
            int maxLength = Math.min(left.length(), right.length());
            for (int i = 0; i < maxLength; i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return left.substring(0, i);
                }
            }
            return left.substring(0, maxLength);
        }

        // 遍历所有字符串，从前缀第一个字符开始比较；我觉得没有充分利用局部性原理，性能应该低一点；
        public static String longestCommonPrefix2(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            int count = strs.length;
            for (int i = 0; i < strs[0].length(); i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < count; j++) {
                    if (i == strs[j].length() || c != strs[j].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }

        // 使用递归的思路去
        public static String longestCommonPrefix3(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int count = strs.length;
            return computePrefix(strs, 0, count - 1);
        }

        private static String computePrefix(String[] strs, int left, int right) {
            if (left == right) {
                return strs[left];
            }

            int mid = left + (right - left) / 2;
            String leftString = computePrefix(strs, left, mid);
            String rightString = computePrefix(strs, mid + 1, right);
            return computePrefix(leftString, rightString);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}