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
            while (left < right) {
                while (left < mid) {
                    if (nums[left] <= nums[mid]) {
                        left++;
                    } else {
                        break;
                    }
                }
                while (mid < right) {
                    if (nums[right] >= nums[mid]) {
                        right--;
                    } else {
                        break;
                    }
                }
                if (left < mid && mid < right) {
                    swap(nums, left, right);
                    left++;
                    right--;
                } else if (left < mid) {
                    if (left + 1 == mid) {
                        swap(nums, left, mid);
                    } else {
                        swap(nums, mid, mid - 1);
                        swap(nums, left, mid);
                    }
                    mid--;
                } else if (mid < right) {
                    if (right - 1 == mid) {
                        swap(nums, mid, right);
                    } else {
                        swap(nums, mid, mid + 1);
                        swap(nums, mid, right);
                    }
                    mid++;
                }
            }
            split(nums, start, mid - 1);
            split(nums, mid + 1, end);
        }
    }
}
