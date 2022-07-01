package cn;

//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
//
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 1722 👎 0


public class KthLargestElementInAnArray_215 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        Solution solution = new Solution();
        int kthLargest = solution.findKthLargest(nums, 4);
        System.out.println(kthLargest);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int[] heap = new int[k];
            System.arraycopy(nums, 0, heap, 0, k);
            initHeap(heap, k);
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > heap[0]) {
                    heap[0] = nums[i];
                    adjustHead(heap, k, 0);
                }
            }
            return heap[0];
        }

        /**
         * 将数组调整成一个小根堆
         */
        private void initHeap(int[] heap, int k) {
            for (int i = k - 1; i >= 0; i--) {
                adjustHead(heap, k, i);
            }
        }

        private void adjustHead(int[] heap, int k, int index) {
            if (index * 2 + 1 >= k) {
                return;
            } else if (index * 2 + 1 < k && index * 2 + 2 >= k) {
                if (heap[index] > heap[index * 2 + 1]) {
                    swap(heap, index, index * 2 + 1);
                }
            } else {
                if (heap[index * 2 + 1] > heap[index * 2 + 2]) {
                    if (heap[index] > heap[index * 2 + 2]) {
                        swap(heap, index, index * 2 + 2);
                        adjustHead(heap, k, index * 2 + 2);
                    }
                } else {
                    if (heap[index] > heap[index * 2 + 1]) {
                        swap(heap, index, index * 2 + 1);
                        adjustHead(heap, k, index * 2 + 1);
                    }
                }
            }
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}