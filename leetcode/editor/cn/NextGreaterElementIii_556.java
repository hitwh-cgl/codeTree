package cn;

//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
//
// 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：21
// 
//
// 示例 2： 
//
// 
//输入：n = 21
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 数学 双指针 字符串 
// 👍 228 👎 0


import java.util.ArrayList;
import java.util.List;

public class NextGreaterElementIii_556 {
    public static void main(String[] args) {
        Integer num = 132;
        Solution solution = new Solution();
        solution.nextGreaterElement(num);

    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nextGreaterElement(int n) {
            List<Integer> list = new ArrayList<>();
            split(n, list);
            if (adjust(list) == -1) {
                return -1;
            }
            return parseInt(list);
        }

        /**
         * 从低位到高位装填每位上的数字到list里面;
         */
        private void split(int n, List<Integer> list) {
            // 112
            while (n >= 10) {
                list.add(n % 10);
                n = n / 10;
            }
            list.add(n);
        }

        /**
         * log(n)
         * 1.size==1 2.本身非递减 -1;
         */
        private int adjust(List<Integer> list) {
            if (list.size() == 1) {
                return -1;
            }

            int preIndex = 0, curIndex = 1;
            while (curIndex < list.size()) {
                if (list.get(preIndex) <= list.get(curIndex)) {
                    preIndex++;
                    curIndex++;
                    if (curIndex == list.size()) {
                        return -1;
                    }
                } else {
                    // curIndex是需要调整的位置，找到前序最小的满足元素
                    while (preIndex - 1 >= 0 && list.get(preIndex - 1) > list.get(curIndex)) {
                        preIndex--;
                    }
                    swap(list, preIndex, curIndex);
                    int left = 0, right = curIndex - 1;
                    // 0 preIndex 非递增排序， 需要改成非递减排序;
                    while (left < right) {
                        swap(list, left, right);
                        left++;
                        right--;
                    }
                    break;
                }
            }
            return 0;
        }

        private void swap(List<Integer> list, int index1, int index2) {
            int temp = list.get(index1);
            list.set(index1, list.get(index2));
            list.set(index2, temp);
        }

        /**
         * 根据各位的数字拼装成int;
         * 数字溢出判断;
         */
        private int parseInt(List<Integer> list) {
            int answer = 0;
            for (int i = list.size() - 1; i >= 0; i--) {
                // answer * 10 + list.get(i) > Integer.MAX_VALUE;
                if (answer > (Integer.MAX_VALUE - list.get(i)) / 10) {
                    return -1;
                }
                answer = answer * 10 + list.get(i);
            }
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}