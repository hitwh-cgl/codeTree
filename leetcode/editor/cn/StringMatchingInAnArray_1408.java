package cn;

//给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
//
// 如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串
//。 
//
// 
//
// 示例 1： 
//
// 输入：words = ["mass","as","hero","superhero"]
//输出：["as","hero"]
//解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
//["hero","as"] 也是有效的答案。
// 
//
// 示例 2： 
//
// 输入：words = ["leetcode","et","code"]
//输出：["et","code"]
//解释："et" 和 "code" 都是 "leetcode" 的子字符串。
// 
//
// 示例 3： 
//
// 输入：words = ["blue","green","bu"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 30 
// words[i] 仅包含小写英文字母。 
// 题目数据 保证 每个 words[i] 都是独一无二的。 
// 
// Related Topics 字符串 字符串匹配 
// 👍 50 👎 0


import java.util.*;

/**
 * 1.字典树一阵操作，结果耗时过长，哈哈哈
 * 2.看看别人的写法，我觉得可能还是库函数自身的实现效率比较高
 */
public class StringMatchingInAnArray_1408 {

    public static void main(String[] args) {
        String[] words = new String[]{"leetcode", "et", "code"};
        Solution solution = new Solution();
        solution.stringMatching(words);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> stringMatching(String[] words) {
            Arrays.sort(words, Comparator.comparingInt(String::length));

            Set<String> set = new HashSet<>();
            Tire root = new Tire();
            for (String word : words) {

                for (int start = 0; start < word.length(); start++) {
                    int end = start;
                    Tire cur = root;
                    while (end <= word.length()) {
                        if (cur.children.containsKey('*')) {
                            set.add(word.substring(start, end));
                        }
                        if (end == word.length()) {
                            break;
                        }

                        Tire next = cur.children.get(word.charAt(end));
                        if (next != null) {
                            cur = next;
                            end++;
                        } else {
                            break;
                        }
                    }

                }

                Tire cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    Tire next = cur.children.get(ch);
                    if (next == null) {
                        next = new Tire();
                        cur.children.put(ch, next);
                        cur = next;
                    } else {
                        cur = next;
                    }
                }
                cur.children.put('*', null);
            }

            return new ArrayList<>(set);
        }

        static class Tire {
            private Map<Character, Tire> children = new HashMap<>();
        }

        public List<String> stringMatching2(String[] words) {
            StringBuilder builder = new StringBuilder();
            for (String word : words) {
                builder.append(word).append(',');
            }
            List<String> res = new ArrayList<>();
            String total = builder.toString();
            for (String word : words) {
                if (total.indexOf(word) != total.lastIndexOf(word)) {
                    res.add(word);
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}