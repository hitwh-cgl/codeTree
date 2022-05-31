package cn;

//这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr
//[i]。 
//
// 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。 
//
// 注意，不管是什么情况下，你都无法跳到数组之外。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [4,2,3,0,3,1,2], start = 5
//输出：true
//解释：
//到达值为 0 的下标 3 有以下可能方案： 
//下标 5 -> 下标 4 -> 下标 1 -> 下标 3 
//下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3 
// 
//
// 示例 2： 
//
// 输入：arr = [4,2,3,0,3,1,2], start = 0
//输出：true 
//解释：
//到达值为 0 的下标 3 有以下可能方案： 
//下标 0 -> 下标 4 -> 下标 1 -> 下标 3
// 
//
// 示例 3： 
//
// 输入：arr = [3,0,2,1,2], start = 2
//输出：false
//解释：无法到达值为 0 的下标 1 处。 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 5 * 10^4 
// 0 <= arr[i] < arr.length 
// 0 <= start < arr.length 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 
// 👍 116 👎 0


import java.util.Stack;

public class JumpGameIii_1306 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {0,3,0,6,3,3,4};
        solution.canReach(arr, 5);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canReach(int[] arr, int start) {
            // 0,3,0,6,3,3,4
            // 0 0 1 0 0 0 1
            int[] reach = new int[arr.length];
            Stack<Integer> stack = new Stack<>();
            stack.push(start);
            reach[start] = 1;
            while (!stack.isEmpty()) {
                Integer cur = stack.pop();
                if (cur - arr[cur] >= 0 && reach[cur - arr[cur]] == 0) {
                    reach[cur - arr[cur]] = 1;
                    stack.push(cur - arr[cur]);
                }
                if (cur + arr[cur] < arr.length && reach[cur + arr[cur]] == 0) {
                    reach[cur + arr[cur]] = 1;
                    stack.push(cur + arr[cur]);
                }
            }

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0 && reach[i] == 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}