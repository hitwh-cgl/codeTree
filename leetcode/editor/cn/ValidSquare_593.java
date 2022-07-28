package cn;

//给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
//
// 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。 
//
// 一个 有效的正方形 有四条等边和四个等角(90度角)。 
//
// 
//
// 示例 1: 
//
// 
//输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
//输出: True
// 
//
// 示例 2: 
//
// 
//输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
//输出：false
// 
//
// 示例 3: 
//
// 
//输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
//输出：true
// 
//
// 
//
// 提示: 
//
// 
// p1.length == p2.length == p3.length == p4.length == 2 
// -104 <= xi, yi <= 104 
// 
// Related Topics 几何 数学 
// 👍 104 👎 0


import java.util.Arrays;

public class ValidSquare_593 {

    public static void main(String[] args) {
        int[] p1 = new int[]{0, 0};
        int[] p2 = new int[]{4, 0};
        int[] p3 = new int[]{2, 1};
        int[] p4 = new int[]{-2, 1};
        Solution solution = new Solution();
        boolean valid = solution.validSquare(p1, p2, p3, p4);
        System.out.println(valid);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            int[] value = new int[6];
            value[0] = computeDistance(p1, p2);
            value[1] = computeDistance(p1, p3);
            value[2] = computeDistance(p1, p4);
            value[3] = computeDistance(p2, p3);
            value[4] = computeDistance(p2, p4);
            value[5] = computeDistance(p3, p4);
            Arrays.sort(value);
            return !same && value[0] == value[1] && value[0] == value[2] && value[0] == value[3] && value[4] == value[5];
        }

        private boolean same = false;

        private int computeDistance(int[] left, int[] right) {
            if (left[0] == right[0] && left[1] == right[1]) {
                same = true;
            }
            return (left[0] - right[0]) * (left[0] - right[0]) + (left[1] - right[1]) * (left[1] - right[1]);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}