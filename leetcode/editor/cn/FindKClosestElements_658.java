package cn;

//给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
//
// 整数 a 比整数 b 更接近 x 需要满足： 
//
// 
// |a - x| < |b - x| 或者 
// |a - x| == |b - x| 且 a < b 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,4,5], k = 4, x = 3
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,2,3,4,5], k = 4, x = -1
//输出：[1,2,3,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= arr.length 
// 1 <= arr.length <= 104 
// arr 按 升序 排列 
// -104 <= arr[i], x <= 104 
// 
// Related Topics 数组 双指针 二分查找 排序 堆（优先队列） 
// 👍 425 👎 0


import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements_658 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findClosestElements(new int[]{0, 1, 2, 2, 2, 3, 6, 8, 8, 9}, 5, 9);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int resultLeft = 0;
            if (arr[0] >= x) {
                resultLeft = 0;
            } else if (arr[arr.length - 1] <= x) {
                // 12345 x=6 k=2
                resultLeft = arr.length - k;
            } else {
                int left = 0;
                int right = k - 1;
                while (right + 1 < arr.length) {
                    if (Math.abs(arr[right + 1] - x) <= Math.abs(arr[left] - x)) {
                        left++;
                        right++;
                        resultLeft = left;
                    } else {
                        break;
                    }
                }

                int resultRight = resultLeft + k - 1;
                while (resultLeft > 0 && Math.abs(arr[resultLeft - 1] - x) == Math.abs(arr[resultRight] - x)) {
                    resultLeft--;
                    resultRight--;
                }
            }

            List<Integer> result = new ArrayList<>(k);
            for (int i = resultLeft; i < resultLeft + k; i++) {
                result.add(arr[i]);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}