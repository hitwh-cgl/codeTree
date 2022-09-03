package cn;

//给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
//
// 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
//
// 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
//
//
//
// 示例：
//
//
//输入：[[1,2], [2,3], [3,4]]
//输出：2
//解释：最长的数对链是 [1,2] -> [3,4]
//
//
//
//
// 提示：
//
//
// 给出数对的个数在 [1, 1000] 范围内。
//
// Related Topics 贪心 数组 动态规划 排序
// 👍 332 👎 0


import java.util.Arrays;

public class MaximumLengthOfPairChain_646 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findVersion1(new int[][]{{1, 2}, {2, 3}, {3, 4}});
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLongestChain(int[][] pairs) {
            return 0;
        }

        /**
         * 排序+贪心
         */
        public int findVersion1(int[][] pairs) {
            if (pairs.length == 1) {
                return 1;
            }

            Arrays.sort(pairs, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            });
            int count = 1;
            int minLeft = pairs[0][0];
            int minRight = pairs[0][1];
            for (int i = 1; i < pairs.length; i++) {
                int[] cur = pairs[i];
                if (cur[0] > minLeft) {
                    // 1 4 2 3
                    if (cur[1] < minRight) {
                        minLeft = cur[0];
                        minRight = cur[1];
                    } else {
                        if (minRight < cur[0]) {
                            count++;
                            minLeft = cur[0];
                            minRight = cur[1];
                        }
                    }
                }

            }
            return count;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}