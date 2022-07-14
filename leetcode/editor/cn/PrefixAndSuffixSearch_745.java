package cn;

//设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
//
// 实现 WordFilter 类： 
//
// 
// WordFilter(string[] words) 使用词典中的单词 words 初始化对象。 
// f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，
//返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["WordFilter", "f"]
//[[["apple"]], ["a", "e"]]
//输出
//[null, 0]
//解释
//WordFilter wordFilter = new WordFilter(["apple"]);
//wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
//
// 
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 104 
// 1 <= words[i].length <= 7 
// 1 <= pref.length, suff.length <= 7 
// words[i]、pref 和 suff 仅由小写英文字母组成 
// 最多对函数 f 执行 104 次调用 
// 
// Related Topics 设计 字典树 字符串 
// 👍 116 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrefixAndSuffixSearch_745 {

    public static void main(String[] args) {
        WordFilter wordFilter = new WordFilter(new String[]{"apple", "end", "banana", "addle"});
        System.out.println(wordFilter.f("ap", "e"));//0
        System.out.println(wordFilter.f("ap", "le")); // 0
        System.out.println(wordFilter.f("ap", "xe"));// -1
        System.out.println(wordFilter.f("a", "e"));// 3
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class WordFilter {

        private Tire prefixRoot = new Tire();
        private Tire suffixRoot = new Tire();
        private Map<String, Map<String, Integer>> cache = new HashMap<>();

        public WordFilter(String[] words) {
            Map<String, Integer> wordDict = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                wordDict.put(words[i], i);
            }

            for (Map.Entry<String, Integer> entry : wordDict.entrySet()) {
                String word = entry.getKey();
                int i = entry.getValue();

                Tire cur = prefixRoot;
                for (int w = 0; w < word.length(); w++) {
                    cur.children.putIfAbsent(word.charAt(w), new Tire());
                    cur.children.get(word.charAt(w)).indexList.add(i);
                    cur = cur.children.get(word.charAt(w));
                }

                cur = suffixRoot;
                for (int w = word.length() - 1; w >= 0; w--) {
                    cur.children.putIfAbsent(word.charAt(w), new Tire());
                    cur.children.get(word.charAt(w)).indexList.add(i);
                    cur = cur.children.get(word.charAt(w));
                }
            }
        }

        public int f(String pref, String suff) {
            if (cache.get(pref) != null && cache.get(pref).get(suff) != null) {
                return cache.get(pref).get(suff);
            }

            Set<Integer> prefixSet;
            Tire cur = prefixRoot;
            for (int i = 0; i < pref.length(); i++) {
                cur = cur.children.get(pref.charAt(i));
                if (cur == null) {
                    break;
                }
            }
            if (cur != null) {
                prefixSet = cur.indexList;
            } else {
                return -1;
            }

            cur = suffixRoot;
            for (int i = suff.length() - 1; i >= 0; i--) {
                cur = cur.children.get(suff.charAt(i));
                if (cur == null) {
                    break;
                }
            }
            if (cur != null) {
                int maxIndex = -1;
                for (Integer index : cur.indexList) {
                    if (prefixSet.contains(index)) {
                        maxIndex = Math.max(maxIndex, index);
                    }
                }
                cache.putIfAbsent(pref, new HashMap<>());
                cache.get(pref).putIfAbsent(suff, maxIndex);
                return maxIndex;
            } else {
                return -1;
            }
        }

        static class Tire {
            Map<Character, Tire> children = new HashMap<>();
            Set<Integer> indexList = new HashSet<>();
        }
    }

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
//leetcode submit region end(Prohibit modification and deletion)

}