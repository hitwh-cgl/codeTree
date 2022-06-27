package cn;

//给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
//
// 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。 
//
// s 的 子序列可以通过删去字符串 s 中的某些字符实现。 
//
// 
// 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"
//、 "aeb" 和 "" (空字符串)。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入: strs = ["aba","cdc","eae"]
//输出: 3
// 
//
// 示例 2: 
//
// 
//输入: strs = ["aaa","aaa","aa"]
//输出: -1
// 
//
// 
//
// 提示: 
//
// 
// 2 <= strs.length <= 50 
// 1 <= strs[i].length <= 10 
// strs[i] 只包含小写英文字母 
// 
// Related Topics 数组 哈希表 双指针 字符串 排序 
// 👍 104 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.我的思路比较复杂，先按照长度分类，然后长度从大到小去判断是否是最长特殊序列；
 * 1.1如果字符串都相同，那么该长度的字符串都不行，但是也要排除后面他的字串；
 * 2.如果存在某个字符串唯一，那么它就是最长特殊序列；
 * <p>
 * 2.官方思路会比较简单，按照判断每个字符串是否是别的字符串的子串就行，然后返回最长的那个唯一字符串;
 */
public class LongestUncommonSubsequenceIi_522 {
    public static void main(String[] args) {
        String[] strs = new String[]{"aba", "cdc", "eae"};
        Solution solution = new Solution();
        solution.findLUSlength(strs);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLUSlength(String[] strs) {
            Map<Integer, List<String>> lengthMap = new HashMap<>();
            int maxLength = 1;
            for (String cur : strs) {
                lengthMap.putIfAbsent(cur.length(), new ArrayList<>());
                lengthMap.get(cur.length()).add(cur);
                maxLength = Math.max(maxLength, cur.length());
            }

            for (int curLength = maxLength; curLength > 0; curLength--) {
                List<String> strList = lengthMap.get(curLength);
                if (strList != null) {
                    if (isAllSame(strList, curLength)) {
                        sameList.add(strList.get(0));
                    } else {
                        if (!isAllExist(strList)) {
                            return curLength;
                        }
                    }
                }
            }
            return -1;
        }

        private List<String> sameList = new ArrayList<>();

        private boolean isAllSame(List<String> strList, int length) {
            if (strList.size() == 1) {
                return false;
            }

            for (int i = 0; i < length; i++) {
                int[] count = new int[26];
                for (String str : strList) {
                    count[str.charAt(i) - 'a']++;
                }
                for (int j = 0; j < 26; j++) {
                    if (count[j] == 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isAllExist(List<String> result) {
            for (String cur : result) {
                boolean exist = false;
                for (String before : sameList) {
                    int l1 = 0, l2 = 0;
                    while (l1 < cur.length() && l2 < before.length()) {
                        while (l2 < before.length() && before.charAt(l2) != cur.charAt(l1)) {
                            l2++;
                        }
                        while (l1 < cur.length() && l2 < before.length() && before.charAt(l2) == cur.charAt(l1)) {
                            l1++;
                            l2++;
                        }
                    }
                    if (l1 == cur.length()) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    return false;
                }
            }
            return true;
        }


        public int findLUSlengthV2(String[] strs) {
            int res = -1;
            for (int i = 0; i < strs.length; i++) {
                boolean success = true;
                for (int j = 0; j < strs.length; j++) {
                    if (i != j && isSubStr(strs[i], strs[j])) {
                        success = false;
                        break;
                    }
                }
                if (success) {
                    res = Math.max(res, strs[i].length());
                }
            }
            return res;
        }

        private boolean isSubStr(String son, String father) {
            if (father.length() < son.length()) {
                return false;
            }
            int sl = 0, fl = 0;
            while (sl < son.length() && fl < father.length()) {
                while (fl < father.length() && father.charAt(fl) != son.charAt(sl)) {
                    fl++;
                }
                while (fl < father.length() && sl < son.length() && father.charAt(fl) == son.charAt(sl)) {
                    fl++;
                    sl++;
                }
            }
            return sl == son.length();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}