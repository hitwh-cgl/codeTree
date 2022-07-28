package cn;

//给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
//
// 序号代表了一个元素有多大。序号编号的规则如下： 
//
// 
// 序号从 1 开始编号。 
// 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。 
// 每个数字的序号都应该尽可能地小。 
// 
//
// 
//
// 示例 1： 
//
// 输入：arr = [40,10,20,30]
//输出：[4,1,2,3]
//解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。 
//
// 示例 2： 
//
// 输入：arr = [100,100,100]
//输出：[1,1,1]
//解释：所有元素有相同的序号。
// 
//
// 示例 3： 
//
// 输入：arr = [37,12,28,9,100,56,80,5,12]
//输出：[5,3,4,2,8,6,7,1,3]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= arr.length <= 105 
// -109 <= arr[i] <= 109 
// 
// Related Topics 数组 哈希表 排序 
// 👍 93 👎 0


import java.util.Arrays;

/**
 * 1.正常思路可以参考提交记录，就是拷贝、排序然后使用Map记录；
 * 2.这题比较坑的是，1223对应的是：1223，而不是1224；
 * 3.下面这个答案是我抄的，执行时间更短，20ms->13ms，本质是通过线性寻址的方式实现的HashMap;
 *
 * @author 17862
 */
public class RankTransformOfAnArray_1331 {

    public static void main(String[] args) {
        int[] arr = new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12};
        Solution solution = new Solution();
        int[] result = solution.arrayRankTransform(arr);
        System.out.println(result);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] arrayRankTransform(int[] arr) {
            int n = arr.length;
            if (n == 0) {
                return arr;          //当数组大小为0时, 直接原样返回
            }
            if (n == 1) {
                arr[0] = 1;
                return arr;
            }
            // 备份原始数组
            int[] sort = Arrays.copyOf(arr, n);
            Arrays.sort(sort); //排序
            IntMap map = new IntMap();
            int rank = 1;
            map.put(sort[0], rank++);
            for (int i = 1; i < n; i++) {
                if (sort[i] != sort[i - 1]) {
                    map.put(sort[i], rank++);
                }
            }
            //遍历原始数组, 从map获取对应顺序, 并填入原位数组
            for (int j = 0; j < n; j++) {
                arr[j] = map.get(arr[j]);
            }
            return arr;
        }

        static class IntMap {
            // pow(2,18) = 1024*256
            static int[] KV = new int[1 << 18];

            public int get(int k) {
                return KV[pos(k) + 1];
            }

            public void put(int k, int v) {
                int p = pos(k);
                KV[p] = k;
                KV[p + 1] = v;
            }

            private int pos(int k) {
                int n = KV.length, i = k * 2 & (n - 1);
                while (KV[i] != k && KV[i + 1] != 0) {
                    i = (i + 2) & (n - 1);
                }
                return i;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}