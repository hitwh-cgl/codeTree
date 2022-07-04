package sort;

public class InsertSort extends BaseSort {

    @Override
    public void sort(int[] nums) {
        // i: 待移动元素下标
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    int temp = nums[i];
                    System.arraycopy(nums, j, nums, j + 1, i - j);
                    nums[j] = temp;
                }
            }
        }
    }
}
