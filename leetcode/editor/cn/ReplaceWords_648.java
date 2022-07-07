package cn;

//在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词
//根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。 
//
// 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继
//承词有许多可以形成它的词根，则用最短的词根替换它。 
//
// 你需要输出替换之后的句子。 
//
// 
//
// 示例 1： 
//
// 
//输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the
// battery"
//输出："the cat was rat by the bat"
// 
//
// 示例 2： 
//
// 
//输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//输出："a a b c"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 100 
// dictionary[i] 仅由小写字母组成。 
// 1 <= sentence.length <= 10^6 
// sentence 仅由小写字母和空格组成。 
// sentence 中单词的总量在范围 [1, 1000] 内。 
// sentence 中每个单词的长度在范围 [1, 1000] 内。 
// sentence 中单词之间由一个空格隔开。 
// sentence 没有前导或尾随空格。 
// 
//
// 
// Related Topics 字典树 数组 哈希表 字符串 
// 👍 196 👎 0


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReplaceWords_648 {
    public static void main(String[] args) {
        List<String> dictionary = Stream.of("uxbiw", "pb", "zmeno", "bj", "tdjn", "fcomt", "rdd", "z", "d", "i", "gxmxj", "swga", "t", "g", "bjoz", "siyi", "fpp", "gpied", "qjf", "h", "dorm", "zd", "gx", "viczg", "dewq", "tz", "dwyxy", "o", "rtcq", "j").collect(Collectors.toList());
        String sentence = "cfrbiqbqzveiczjn miwz hv uslvci vuhgzbulkiurzxkiqe nqg rccocvwfp sntmlrrdsqwpvem iyrw kbqwjkfichfrejx lhzylxmbptiwmn v nodg xijddmenifxaffhxxx hpltrapstesvkrnjoqdl mygwsjmgzzoixyo xcnyhbmpkpamtzdrjls wtuddincttwfnai cgxlvdww yqhnapyzkv nmrvpd poimszzov epmiddarizx tlhkwz pqq ardwiezm iowkiammwm ewlotixpfsbhkwphaiv ehitgi eczvbyheauzvho";
        Solution solution = new Solution();
        String replaceWords = solution.replaceWords(dictionary, sentence);
        String expect = "cfrbiqbqzveiczjn miwz h uslvci vuhgzbulkiurzxkiqe nqg rccocvwfp sntmlrrdsqwpvem i kbqwjkfichfrejx lhzylxmbptiwmn v nodg xijddmenifxaffhxxx h mygwsjmgzzoixyo xcnyhbmpkpamtzdrjls wtuddincttwfnai cgxlvdww yqhnapyzkv nmrvpd poimszzov epmiddarizx t pqq ardwiezm i ewlotixpfsbhkwphaiv ehitgi eczvbyheauzvho";
        System.out.println(replaceWords.equals(expect));
        System.out.println(sentence);
        System.out.println(expect);
        System.out.println(replaceWords);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String replaceWords(List<String> dictionary, String sentence) {
            Tire root = initTree(dictionary);
            return Arrays.stream(sentence.split(" ")).map(e -> searchTreeAndAppend(root, e)).collect(Collectors.joining(" "));
        }

        private String searchTreeAndAppend(Tire root, String word) {
            StringBuilder builder = new StringBuilder();
            for (char cur : word.toCharArray()) {
                if (root.children.containsKey('#')) {
                    break;
                }
                if (root.children.containsKey(cur)) {
                    root = root.children.get(cur);
                    builder.append(cur);
                } else {
                    return word;
                }
            }
            return builder.toString();
        }

        private Tire initTree(List<String> dictionary) {
            Tire root = new Tire();
            for (String word : dictionary) {
                Tire curNode = root;
                for (char cur : word.toCharArray()) {
                    curNode.children.putIfAbsent(cur, new Tire());
                    curNode = curNode.children.get(cur);
                }
                curNode.children.put('#', null);
            }
            return root;
        }

        public static class Tire {
            private Map<Character, Tire> children;

            public Tire() {
                children = new HashMap<>();
            }
        }

        /**
         * 尝试用有序集合加快查询速度
         * 195ms->91ms，算是有用？
         */
        public String v2(List<String> dictionary, String sentence) {
            TreeSet<String> set = new TreeSet<>(dictionary);
            StringBuilder builder = new StringBuilder();
            String[] words = sentence.split(" ");
            for (String cur : words) {
                String before = set.floor(cur);
                // 这里一开始用contains，存在问题，比如 kj 查出了j,但是 j不是前缀，不行
                if (before != null && cur.startsWith(before)) {
                    String append = before;
                    for (int j = before.length() - 1; j >= 1; j--) {
                        String shorter = before.substring(0, j);
                        if (set.contains(shorter)) {
                            append = shorter;
                        } else {
                            break;
                        }
                    }
                    builder.append(append).append(" ");
                } else {
                    prefixSearch(set, cur, builder);
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }

        private void prefixSearch(Set<String> set, String cur, StringBuilder builder) {
            boolean match = false;
            for (int endIndex = 1; endIndex <= cur.length(); endIndex++) {
                String root = cur.substring(0, endIndex);
                if (set.contains(root)) {
                    builder.append(root).append(" ");
                    match = true;
                    break;
                }
            }
            if (!match) {
                builder.append(cur).append(" ");
            }
        }

        /**
         * 暴力
         */
        public String v1(List<String> dictionary, String sentence) {
            HashSet<String> set = new HashSet<>(dictionary);
            StringBuilder builder = new StringBuilder();
            String[] words = sentence.split(" ");
            for (String cur : words) {
                prefixSearch(set, cur, builder);
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}