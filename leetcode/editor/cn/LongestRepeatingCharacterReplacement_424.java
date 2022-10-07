package cn;

//给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
//
// 在执行上述操作后，返回包含相同字母的最长子字符串的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ABAB", k = 2
//输出：4
//解释：用两个'A'替换为两个'B',反之亦然。
// 
//
// 示例 2： 
//
// 
//输入：s = "AABABBA", k = 1
//输出：4
//解释：
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 105 
// s 仅由大写英文字母组成 
// 0 <= k <= s.length 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 670 👎 0


public class LongestRepeatingCharacterReplacement_424 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int max = solution.characterReplacement("AABAB", 1);
        System.out.println(max);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int characterReplacement(String s, int k) {
            char[] value = s.toCharArray();
            int[] count = new int[26];
            int max = 0;
            int left = 0, right = 0;
            int answer = 0;
            while (right < s.length()) {
                count[value[right] - 'A']++;
                max = Math.max(max, count[value[right] - 'A']);
                while (max + k < right - left + 1) {
                    count[value[left] - 'A']--;
                    left++;
                }
                answer = right - left + 1;
                right++;
            }
            return answer;
        }

        public int version1(String s, int k) {
            boolean[] possible = new boolean[26];
            for (char ch : s.toCharArray()) {
                possible[ch - 'A'] = true;
            }

            int max = 1;
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                if (possible[ch]) {
                    int cur = change(s, k, ch);
                    max = Math.max(max, cur);
                }
            }
            return max;
        }

        private int change(String s, int k, char ch) {
            int left = 0, right = 0;
            int count = s.charAt(0) == ch ? 0 : 1;
            int max = 0;
            while (right < s.length()) {
                right++;
                if (right < s.length()) {
                    count += s.charAt(right) == ch ? 0 : 1;
                }
                while (count > k && left <= right) {
                    count -= s.charAt(left) == ch ? 0 : 1;
                    left++;
                }
                if (right < s.length()) {
                    max = Math.max(max, right - left + 1);
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}