package sort;

/**
 * @author 17862
 */
public abstract class BaseSort {

    /**
     * 默认升序排列
     *
     * @param nums 待排序数组
     */
    public abstract void sort(int[] nums);

    protected void swap(int[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
}
