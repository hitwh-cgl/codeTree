package cn;

//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。 
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
//输入: rowIndex = 3
//输出: [1,3,3,1]
// 
//
// 示例 2: 
//
// 
//输入: rowIndex = 0
//输出: [1]
// 
//
// 示例 3: 
//
// 
//输入: rowIndex = 1
//输出: [1,1]
// 
//
// 
//
// 提示: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？ 
// Related Topics 数组 动态规划 
// 👍 398 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangleIi_119 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            return method2(rowIndex);
        }

        public List<Integer> method1(int rowIndex) {
            Integer[] array = new Integer[rowIndex + 1];
            for (int i = 1; i <= rowIndex + 1; i++) {
                for (int j = i; j >= 1; j--) {
                    if (j == 1 || j == i) {
                        array[j - 1] = 1;
                    } else {
                        array[j - 1] = array[j - 2] + array[j - 1];
                    }
                }
            }
            return Arrays.asList(array);
        }

        public List<Integer> method2(int rowIndex) {
            List<Integer> ans = new ArrayList<>(rowIndex + 1);
            for (int i = 1; i <= rowIndex + 1; i++) {
                for (int j = i; j >= 1; j--) {
                    if (j == 1 || j == i) {
                        if (ans.size() <= j - 1) {
                            ans.add(j - 1, 1);
                        } else {
                            ans.set(j - 1, 1);
                        }
                    } else {
                        ans.set(j - 1, ans.get(j - 1) + ans.get(j - 2));
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}