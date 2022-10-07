package mst;

/**
 * 21 25 27
 *
 * @author 17862
 */
public class MST_1709 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i <= 10; i++) {
            System.out.println(solution.getKthMagicNumber(i));
        }
    }

    static class Solution {
        public int getKthMagicNumber(int k) {
            int[] min = new int[k + 1];
            min[1] = 1;
            int p3 = 1, p5 = 1, p7 = 1;
            for (int i = 2; i <= k; i++) {
                min[i] = Math.min(Math.min(min[p3] * 3, min[p5] * 5), min[p7] * 7);
                if (min[i] == min[p3] * 3) {
                    p3++;
                }
                if (min[i] == min[p5] * 5) {
                    p5++;
                }
                if (min[i] == min[p7] * 7) {
                    p7++;
                }
            }
            return min[k];
        }
    }
}
