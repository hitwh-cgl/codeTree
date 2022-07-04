package cn;

//给你个整数数组 arr，其中每个元素都 不相同。
//
// 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [4,2,1,3]
//输出：[[1,2],[2,3],[3,4]]
// 
//
// 示例 2： 
//
// 输入：arr = [1,3,6,10,15]
//输出：[[1,3]]
// 
//
// 示例 3： 
//
// 输入：arr = [3,8,-10,23,19,-4,-14,27]
//输出：[[-14,-10],[19,23],[23,27]]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= arr.length <= 10^5 
// -10^6 <= arr[i] <= 10^6 
// 
// Related Topics 数组 排序 
// 👍 95 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference_1200 {
    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> result = solution.minimumAbsDifference(arr);
        System.out.println(result.size());
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 我自己实现的版本
         */
        public List<List<Integer>> count(int[] arr) {
            int min = 1000000, max = -1000000;
            for (int cur : arr) {
                min = Math.min(min, cur);
                max = Math.max(max, cur);
            }

            boolean[] count = new boolean[max - min + 1];
            int base = -min;
            for (int cur : arr) {
                count[base + cur] = true;
            }

            int prev = min;
            int minAbsolute = 2000000;
            for (int i = min + 1; i <= max; i++) {
                if (count[base + i]) {
                    minAbsolute = Math.min(minAbsolute, i - prev);
                    if (minAbsolute == 1) {
                        break;
                    } else {
                        prev = i;
                    }
                }
            }

            List<List<Integer>> result = new ArrayList<>(arr.length - 1);
            for (int i = min; i <= max; i++) {
                if (count[base + i] && i + minAbsolute <= max && count[base + i + minAbsolute]) {
                    List<Integer> list = new ArrayList<>(2);
                    list.add(i);
                    list.add(i + minAbsolute);
                    result.add(list);
                }
            }
            return result;
        }

        /**
         * 大神的版本，太快了，怎么做到的？
         */
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            return new java.util.AbstractList<List<Integer>>() {
                private int[] buffer;
                private int size;
                private boolean initialed;
                private int min = Integer.MAX_VALUE;

                private void init() {
                    if (initialed) {
                        return;
                    }
                    buffer = new int[arr.length];
                    Arrays.sort(arr);
                    for (int i = 1; i < arr.length; i++) {
                        int diff = arr[i] - arr[i - 1];
                        if (diff > min) {
                            continue;
                        } else if (diff < min) {
                            size = 0;
                            min = diff;
                        }
                        buffer[size++] = arr[i - 1];
                    }
                    initialed = true;
                }

                @Override
                public List<Integer> get(int index) {
                    init();
                    return Arrays.asList(buffer[index], buffer[index] + min);
                }

                @Override
                public int size() {
                    init();
                    return size;
                }
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}