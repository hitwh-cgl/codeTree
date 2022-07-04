package sort;

/**
 * 归并排序
 *
 * @author 17862
 */
public class MergeSort extends BaseSort {

    @Override
    public void sort(int[] nums) {
        int[] temp = new int[nums.length];
        divide(nums, 0, nums.length, temp);
    }

    private void divide(int[] array, int left, int right, int[] temp) {
        if (right > left) {
            int mid = (left + right) / 2;
            divide(array, left, mid, temp);
            divide(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    private void merge(int[] array, int left, int mid, int right, int[] temp) {
        int p1 = left, p2 = mid + 1, n = 0;
        while (p1 <= mid && p2 <= right) {
            if (array[p1] <= array[p2]) {
                temp[n++] = array[p1++];
            } else {
                temp[n++] = array[p2++];
            }
        }
        while (p1 <= mid) {
            temp[n++] = array[p1++];
        }
        while (p2 <= right) {
            temp[n++] = array[p2++];
        }

        n = 0;
        while (left <= right) {
            array[left++] = temp[n++];
        }
    }
}
