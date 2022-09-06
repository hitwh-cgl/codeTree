package cn;

//我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
//
// 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueCh
//ars(s) = 5 。 
//
// 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整
//数。 
//
// 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "ABC"
//输出: 10
//解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
//     其中，每一个子串都由独特字符构成。
//     所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
// 
//
// 示例 2： 
//
// 
//输入: s = "ABA"
//输出: 8
//解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
// 
//
// 示例 3： 
//
// 
//输入：s = "LEETCODE"
//输出：92
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// s 只包含大写英文字符 
// 
// Related Topics 哈希表 字符串 动态规划 
// 👍 251 👎 0


import java.util.Arrays;

public class CountUniqueCharactersOfAllSubstringsOfAGivenString_828 {
    public static void main(String[] args) {
        String source = "LEETCODE";
        Solution solution = new Solution();
        int i = solution.uniqueLetterString(source);
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniqueLetterString(String s) {
            int n = s.length();
            int total = 1;
            int preCount = 1;
            int[] firstIndex = new int[26];
            Arrays.fill(firstIndex, -1);
            int[] preFirstIndex = new int[26];
            Arrays.fill(preFirstIndex, -1);
            firstIndex[s.charAt(0) - 'A'] = 0;
            for (int i = 1; i < n; i++) {
                int ch = s.charAt(i);
                if (firstIndex[ch - 'A'] == -1) {
                    preCount = preCount + i + 1;
                } else if (preFirstIndex[ch - 'A'] == -1) {
                    int preIndex = firstIndex[ch - 'A'];
                    preCount = preCount + (i - preIndex) - (preIndex + 1);
                    preFirstIndex[ch - 'A'] = preIndex;
                } else {
                    int preIndex = firstIndex[ch - 'A'];
                    int prePreIndex = preFirstIndex[ch - 'A'];
                    preCount = preCount + (i - preIndex) - (preIndex - prePreIndex);
                    preFirstIndex[ch - 'A'] = preIndex;
                }
                total += preCount;
                firstIndex[ch - 'A'] = i;
            }
            return total;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}