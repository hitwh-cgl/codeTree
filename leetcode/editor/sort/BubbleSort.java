package sort;

/**
 * @author 17862
 */
public class BubbleSort extends BaseSort {

    @Override
    public void sort(int[] nums) {
        // i: 本轮确定的底部位置
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                }
            }
        }
    }


}
