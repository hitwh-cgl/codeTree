package cn;

//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 
// 👍 764 👎 0


import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle_118 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<>(numRows);
            List<Integer> pre = new ArrayList<>(1);
            pre.add(1);
            ans.add(pre);
            for (int i = 2; i <= numRows; i++) {
                List<Integer> cur = new ArrayList<>(i);
                for (int j = 1; j <= i; j++) {
                    if (j == 1 || j == i) {
                        cur.add(1);
                    } else {
                        // index need reduce 1
                        cur.add(pre.get(j - 1) + pre.get(j - 2));
                    }
                }
                ans.add(cur);
                pre = cur;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}