package sort;

/**
 * 堆排序
 *
 * @author 17862
 */
public class HeapSort extends BaseSort {
    @Override
    public void sort(int[] nums) {
        initHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            // move the current largest element to the tail;
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }
    }

    private void initHeap(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
    }

    /**
     * @param nums
     * @param index current adjust element;
     * @param k     the min index of element that fixed, we can only move element that less than k;
     */
    private void adjustHeap(int[] nums, int index, int k) {
        int maxIndex = index;
        int leftIndex = index * 2 + 1;
        if (leftIndex < k && nums[leftIndex] > nums[maxIndex]) {
            maxIndex = leftIndex;
        }
        int rightIndex = leftIndex + 1;
        if (rightIndex < k && nums[rightIndex] > nums[maxIndex]) {
            maxIndex = rightIndex;
        }
        if (maxIndex != index) {
            swap(nums, index, maxIndex);
            adjustHeap(nums, maxIndex, k);
        }
    }
}
