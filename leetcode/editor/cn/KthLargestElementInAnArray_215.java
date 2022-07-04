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
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        Solution solution = new Solution();
        int kthLargest = solution.findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快速排序改造
         */
        public int findKthLargest(int[] nums, int k) {
            int left = 0, right = nums.length - 1;
            k = nums.length - k;
            while (left <= right) {
                int pivot = split(nums, left, right);
                if (pivot == k) {
                    return nums[pivot];
                } else if (pivot > k) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            }
            return -1;
        }

        private int split(int[] nums, int left, int right) {
            int mid = left + (right - left) / 2;
            while (left < mid) {
                if (nums[left] <= nums[mid]) {
                    left++;
                } else {
                    if (left + 1 == mid) {
                        swap(nums, mid - 1, mid);
                    } else {
                        swap(nums, left, mid - 1);
                        swap(nums, mid - 1, mid);
                    }
                    mid--;
                }
            }
            while (right > mid) {
                if (nums[right] >= nums[mid]) {
                    right--;
                } else {
                    if (mid + 1 == right) {
                        swap(nums, mid, mid + 1);
                    } else {
                        swap(nums, mid + 1, right);
                        swap(nums, mid, mid + 1);
                    }
                    mid++;
                }
            }
            return mid;
        }

        /**
         * 堆排序改造
         */
        public int findKthLargestWithHeamSort(int[] nums, int k) {
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

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}