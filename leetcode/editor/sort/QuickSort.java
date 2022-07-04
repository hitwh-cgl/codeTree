package sort;

/**
 * 快速排序
 *
 * @author 17862
 */
public class QuickSort extends BaseSort {

    @Override
    public void sort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private void split(int[] nums, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            int left = start, right = end;
            while (left < mid) {
                if (nums[left] <= nums[mid]) {
                    left++;
                } else {
                    if (left == mid - 1) {
                        swap(nums, mid, mid - 1);
                    } else {
                        swap(nums, left, mid - 1);
                        swap(nums, mid, mid - 1);
                    }
                    mid--;
                }
            }
            while (right > mid) {
                if (nums[right] >= nums[mid]) {
                    right--;
                } else {
                    if (right == mid + 1) {
                        swap(nums, mid, mid + 1);
                    } else {
                        swap(nums, right, mid + 1);
                        swap(nums, mid, mid + 1);
                    }
                    mid++;
                }
            }
            split(nums, start, mid - 1);
            split(nums, mid + 1, end);
        }
    }
}
