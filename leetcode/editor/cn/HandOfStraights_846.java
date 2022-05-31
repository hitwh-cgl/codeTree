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


public class HandOfStraights_846 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isNStraightHand(int[] hand, int groupSize) {
            // length check
            if (hand.length % groupSize != 0) {
                return false;
            }
            // choose sort

            // i: how many numbers have been used
            // used: mark the the number is used or not
            int[] used = new int[hand.length];
            for (int i = 0; i < hand.length / groupSize; i++) {
                int currentHeadIndex = Integer.MAX_VALUE;
                // find current straight head, the minimum number that not used
                for (int j = 0; j < hand.length; j++) {
                    if (used[j] == 0
                            && (currentHeadIndex == Integer.MAX_VALUE || hand[j] < hand[currentHeadIndex])) {
                        currentHeadIndex = j;
                    }
                }
                used[currentHeadIndex] = 1;
                // find the left numbers
                for (int n = 1; n < groupSize; n++) {
                    boolean find = false;
                    for (int m = 0; m < hand.length; m++) {
                        if (used[m] == 0 && hand[m] == hand[currentHeadIndex] + 1) {
                            used[m] = 1;
                            currentHeadIndex = m;
                            find = true;
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