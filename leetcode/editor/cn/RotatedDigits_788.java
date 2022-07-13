package cn;

//我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
//
// 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况
//下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。 
//
// 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？ 
//
// 
//
// 示例： 
//
// 输入: 10
//输出: 4
//解释: 
//在[1, 10]中有四个好数： 2, 5, 6, 9。
//注意 1 和 10 不是好数, 因为他们在旋转之后不变。
// 
//
// 
//
// 提示： 
//
// 
// N 的取值范围是 [1, 10000]。 
// 
// Related Topics 数学 动态规划 
// 👍 110 👎 0


public class RotatedDigits_788 {
    public static void main(String[] args) {
        // 2 5 6 9 12 15 16 19 20
        Solution solution = new Solution();
        for (int i = 1; i <= 20; i++) {
            System.out.println(i + "=>" + solution.rotatedDigits(i));
        }
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rotatedDigits(int n) {
            int count = 0;
            boolean[] invalid = new boolean[10];
            invalid[3] = true;
            invalid[4] = true;
            invalid[7] = true;
            boolean[] keep = new boolean[10];
            keep[0] = true;
            keep[1] = true;
            keep[8] = true;
            for (int i = 1; i <= n; i++) {
                int temp = i;
                boolean change = false;
                boolean valid = true;
                while (temp > 0) {
                    int mod = temp % 10;
                    if (invalid[mod]) {
                        valid = false;
                        break;
                    }

                    if (!keep[mod]) {
                        change = true;
                    }
                    temp = temp / 10;
                }
                if (valid && change) {
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}