package cn;

//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 723 👎 0


import java.util.*;

public class SubstringWithConcatenationOfAllWords_30 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        solution.findSubstring(s, words);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            int unit = words[0].length();
            int count = words.length;
            HashMap<String, Integer> wordMap = new HashMap<>(words.length);
            for (String word : words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
            // record index j and if it is possible to became the start;
            boolean[] impossible = new boolean[s.length()];
            // record index j and match word map;
            Map<Integer, Map<String, Integer>> indexMap = new HashMap<>(s.length());
            // record index j and match word count;
            Map<Integer, Integer> timeMap = new HashMap<>(s.length());
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i <= s.length() - unit; i++) {
                String cur = s.substring(i, i + unit);
                Integer time = wordMap.get(cur);
                if (time != null) {
                    for (int j = i; j >= i - unit * (count - 1) && j >= 0; j -= unit) {
                        if (!impossible[j]) {
                            indexMap.putIfAbsent(j, new HashMap<>(wordMap.size()));
                            Map<String, Integer> subCurMap = indexMap.get(j);
                            int existTime = subCurMap.getOrDefault(cur, 0);
                            if (existTime + 1 <= time) {
                                subCurMap.put(cur, existTime + 1);
                                int totalExistTime = timeMap.getOrDefault(j, 0) + 1;
                                timeMap.put(j, totalExistTime);
                                if (totalExistTime == count) {
                                    result.add(j);
                                }
                            } else {
                                impossible[j] = true;
                            }
                        }
                    }
                } else {
                    impossible[i] = true;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}