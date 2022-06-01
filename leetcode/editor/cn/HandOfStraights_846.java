package cn;

//Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。 
//
// 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，
//返回 false 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//输出：true
//解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。 
//
// 示例 2： 
//
// 
//输入：hand = [1,2,3,4,5], groupSize = 4
//输出：false
//解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。 
//
// 
//
// 提示： 
//
// 
// 1 <= hand.length <= 104 
// 0 <= hand[i] <= 109 
// 1 <= groupSize <= hand.length 
// 
//
// 
//
// 注意：此题目与 1296 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-co
//nsecutive-numbers/ 
// Related Topics 贪心 数组 哈希表 排序 
// 👍 203 👎 0


import java.util.Map;
import java.util.TreeMap;

public class HandOfStraights_846 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public boolean isNStraightHand(int[] hand, int groupSize) {
            if (hand.length % groupSize != 0) {
                return false;
            }

            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i : hand) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
            for (int i = 0; i < hand.length / groupSize; i++) {
                Map.Entry<Integer, Integer> first = map.firstEntry();
                int start = first.getKey() - 1;
                for (int j = 0; j < groupSize; j++) {
                    Integer count = map.get(start + 1);
                    if (count == null || count < 1) {
                        return false;
                    }

                    if (count > 1) {
                        map.put(start + 1, count - 1);
                    } else {
                        map.remove(start + 1);
                    }
                    start++;
                }
            }
            return true;
        }

        /**
         * 类似冒泡排序，利于理解和书写；
         */
        public boolean isNStraightHand2(int[] hand, int groupSize) {
            // length check
            if (hand.length % groupSize != 0) {
                return false;
            }
            // bubble sort
            for (int i = 0; i < hand.length; i++) {
                if (i % groupSize == 0) {
                    // find head
                    for (int j = hand.length - 1; j > i; j--) {
                        if (hand[j] < hand[j - 1]) {
                            int temp = hand[j];
                            hand[j] = hand[j - 1];
                            hand[j - 1] = temp;
                        }
                    }
                } else {
                    // find body card
                    boolean find = false;
                    for (int j = i; j < hand.length; j++) {
                        if (hand[j] == hand[i - 1] + 1) {
                            find = true;
                            if (j != i) {
                                int temp = hand[i];
                                hand[i] = hand[j];
                                hand[j] = temp;
                            }
                            break;
                        }
                    }
                    if (!find) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}