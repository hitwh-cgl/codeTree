package cn;

//给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。 
//
// 实现 Solution 类: 
//
// 
// Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置
// (x_center, y_center) 初始化对象 
// randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//["Solution","randPoint","randPoint","randPoint"]
//[[1.0, 0.0, 0.0], [], [], []]
//输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
//解释:
//Solution solution = new Solution(1.0, 0.0, 0.0);
//solution.randPoint ();//返回[-0.02493，-0.38077]
//solution.randPoint ();//返回[0.82314,0.38945]
//solution.randPoint ();//返回[0.36572,0.17248] 
//
// 
//
// 提示： 
//
// 
// 0 < radius <= 108 
// -107 <= x_center, y_center <= 107 
// randPoint 最多被调用 3 * 104 次 
// 
// Related Topics 几何 数学 拒绝采样 随机化 
// 👍 87 👎 0


import java.util.Random;

/**
 * 1.极坐标的思路，角度是没有问题的，但是半径上每个点出现概率是不一样的；
 * 2.在正方形内随机，将不在圆内的点丢弃掉；
 *
 * @author liuchenguang002
 */
public class GenerateRandomPointInACircle_478 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        private Random random = new Random();
        private double redius;
        private double x_center;
        private double y_center;

        public Solution(double radius, double x_center, double y_center) {
            this.redius = radius;
            this.x_center = x_center;
            this.y_center = y_center;
        }

        public double[] randPoint() {
            double x, y;
            do {
                x = random.nextDouble() * 2 * redius - redius;
                y = random.nextDouble() * 2 * redius - redius;
            } while (x * x + y * y > redius * redius);
            return new double[]{x + x_center, y + y_center};
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
//leetcode submit region end(Prohibit modification and deletion)

}