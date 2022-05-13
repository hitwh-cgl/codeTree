package cn;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 7559 👎 0


import java.util.HashMap;

/**
 * 剑指offer 48
 *
 * @author liuchenguang002
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            return 0;
        }

        /**
         * 常规思路
         * 第一次不太容易想到需要记录字串起始位置，用来判断重复时，重复元素是否在当前的字串中
         */
        public int common(String s) {
            int max = 0, start = 0, curLenth = 0;
            HashMap<Character, Integer> map = new HashMap<>(32);
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                if (map.containsKey(c)) {
                    int preIndex = map.get(c);
                    if (preIndex >= start) {
                        start = preIndex + 1;
                        curLenth = i - start + 1;
                    } else {
                        curLenth++;
                        max = Math.max(max, curLenth);
                    }
                    map.put(c, i);
                } else {
                    map.put(c, i);
                    curLenth++;
                    max = Math.max(max, curLenth);
                }
            }
            return max;
        }

        /**
         * 动态规划思路
         */
        public int dp(String s) {
            // a b c a b c b b
            // 0 1 2 3 4 5 6 7
            int max = 0, curLength = 0;
            HashMap<Character, Integer> map = new HashMap<>(32);
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                if (map.containsKey(c)) {
                    // 下面是dp状态转移方程的关键,对于fn来说，如果下一个字符重复，重复的字符无非两种可能，在当前的最长字串中或者不在当前的最长字串中；
                    curLength = Math.min(curLength + 1, i - map.get(c));
                    map.put(c, i);
                } else {
                    curLength++;
                    map.put(c, i);
                }
                max = Math.max(max, curLength);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}