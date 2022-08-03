package cn;

//给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
//
// 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "cba", k = 1
//输出："acb"
//解释：
//在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
//在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
// 
//
// 示例 2： 
//
// 
//输入：s = "baaca", k = 3
//输出："aaabc"
//解释：
//在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
//在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= S.length <= 1000 
// s 只由小写字母组成。 
// 
// Related Topics 数学 字符串 排序 
// 👍 91 👎 0


public class OrderlyQueue_899 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.orderlyQueue("cba", 1));
        System.out.println(solution.orderlyQueue("baaca", 2));
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String orderlyQueue(String s, int k) {
            if (k == 1) {
                int minIndex = 0;
                for (int i = 1; i < s.length(); i++) {
                    if (s.charAt(i) < s.charAt(minIndex)) {
                        minIndex = i;
                    } else if (s.charAt(i) == s.charAt(minIndex)) {
                        for (int offset = 0; offset < s.length(); offset++) {
                            int i1 = (i + offset) % s.length();
                            int i2 = (minIndex + offset) % s.length();
                            if (s.charAt(i1) > s.charAt(i2)) {
                                break;
                            } else if (s.charAt(i1) == s.charAt(i2)) {
                                continue;
                            } else {
                                minIndex = i;
                                break;
                            }
                        }
                    }
                }
                StringBuilder builder = new StringBuilder();
                for (int offset = 0; offset < s.length(); offset++) {
                    int index = (minIndex + offset) % s.length();
                    builder.append(s.charAt(index));
                }
                return builder.toString();
            } else {
                int[] count = new int[26];
                for (char c : s.toCharArray()) {
                    count[c - 'a']++;
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    while (count[i] > 0) {
                        count[i]--;
                        builder.append((char) ('a' + i));
                    }
                }
                return builder.toString();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}