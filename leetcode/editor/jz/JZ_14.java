package jz;

/**
 * 难点在于初始状态的确定，n=3剪成两截最大和是2，而后续迭代时可以只作为一截绳子，最大值是3；因此需要把前n=1,2,3都提前处理好；
 *
 * @author 17862
 */
public class JZ_14 {
    class Solution {
        public int cuttingRope(int n) {
            if (n == 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }

            int[] value = new int[n + 1];
            value[1] = 1; // auxiliary
            value[2] = 2;
            value[3] = 3;
            for (int i = 4; i <= n; i++) {
                for (int j = 2; j < i - 1; j++) {
                    value[i] = Math.max(value[i], j * value[i - j]);
                }
            }
            return value[n];
        }
    }
}
