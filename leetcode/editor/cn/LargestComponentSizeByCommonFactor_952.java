package cn;

//给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
//
// 
// 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记； 
// 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。 
// 
//
// 返回 图中最大连通组件的大小 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//
// 
//输入：nums = [4,6,15,35]
//输出：4
// 
//
// 示例 2： 
//
// 
//
// 
//输入：nums = [20,50,9,63]
//输出：2
// 
//
// 示例 3： 
//
// 
//
// 
//输入：nums = [2,3,6,7,4,12,21,39]
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 1 <= nums[i] <= 105 
// nums 中所有值都 不同 
// 
// Related Topics 并查集 数组 数学 
// 👍 130 👎 0


/**
 * 1.深度优先遍历所有节点，每个节点再判断是否和没有触达的节点相连，时间复杂度 O(n*n*m) n是节点数，m是判断是否存在公共因子比较次数，超时；
 * 2.深度优先搜素速度不行，需要使用并查集的技巧来实现
 *
 * @author 17862
 */
public class LargestComponentSizeByCommonFactor_952 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.largestComponentSize(new int[]{20, 50, 9, 63});
        System.out.println(i);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestComponentSize(int[] nums) {
            int max = 0;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            UnionTree tree = new UnionTree(max);

            for (int num : nums) {
                for (int i = 2; i * i <= num; i++) {
                    if (num % i == 0) {
                        tree.union(num, i);
                        tree.union(num, num / i);
                    }
                }
            }
            int maxLength = 0;
            int[] count = new int[max + 1];
            for (int num : nums) {
                int parent = tree.findParent(num);
                count[parent]++;
                maxLength = Math.max(maxLength, count[parent]);
            }
            return maxLength;
        }
    }

    static class UnionTree {
        private int[] parent;
        private int[] count;

        public UnionTree(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            count = new int[n + 1];
        }

        public void union(int x, int y) {
            int parentX = findParent(x);
            int parentY = findParent(y);
            if (parentX != parentY) {
                if (count[parentX] > count[parentY]) {
                    parent[parentY] = parentX;
                } else if (count[parentX] < count[parentY]) {
                    parent[parentX] = parentY;
                } else {
                    parent[parentY] = parentX;
                    count[parentX]++;
                }
            }
        }

        public int findParent(int x) {
            int p = parent[x];
            if (p != x) {
                parent[x] = findParent(p);
            }
            return parent[x];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}