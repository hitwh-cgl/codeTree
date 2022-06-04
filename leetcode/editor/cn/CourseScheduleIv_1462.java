package cn;

//你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，其中 prere
//quisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。 
//
// 
// 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。 
// 
//
// 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。 
//
// 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条
//件。 
//
// 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
//输出：[false,true]
//解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
//输出：[false,false]
//解释：没有先修课程对，所以每门课程之间是独立的。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]
//]
//输出：[true,true]
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 2 <= numCourses <= 100 
// 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2) 
// prerequisites[i].length == 2 
// 0 <= ai, bi <= n - 1 
// ai != bi 
// 每一对 [ai, bi] 都 不同 
// 先修课程图中没有环。 
// 0 <= ui, vi <= n - 1 
// ui != vi 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 84 👎 0


import java.util.*;

/**
 * Floyd算法每次都是选取中转点，判断别的任意两点是否可以中转；而不是先选取两个终点，判断是否可以通过第三个点中转；
 */
public class CourseScheduleIv_1462 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @param prerequisites quisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
         *                      q[1] 依赖 q[0]
         * @param queries       queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件
         *                      q[1] 是否 依赖 q[0]
         */
        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            boolean[][] depend = new boolean[numCourses][numCourses];
            for (int[] pre : prerequisites) {
                depend[pre[1]][pre[0]] = true;
            }
            for (int temp = 0; temp < numCourses; temp++) {
                for (int i = 0; i < numCourses; i++) {
                    for (int j = 0; j < numCourses; j++) {
                        depend[i][j] |= depend[i][temp] && depend[temp][j];
                    }
                }
            }
            List<Boolean> result = new ArrayList<>(queries.length);
            for (int[] query : queries) {
                result.add(depend[query[1]][query[0]]);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}