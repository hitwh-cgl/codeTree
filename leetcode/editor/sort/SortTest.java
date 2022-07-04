package sort;


/**
 * @author 17862
 */
public class SortTest {

    private static BaseSort sort = new HeapSort();

    public static void main(String[] args) {
        int[] source = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        int[] target = {0, 0, 1, 1, 2, 5};
        sort.sort(source);
        for (int i = 0; i < source.length; i++) {
            System.out.println(source[i] == target[i]);
            System.out.println(source[i] + "/" + target[i] + ";");
        }
    }


}
