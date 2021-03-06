package cn;

//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 1268 👎 0


import java.util.*;

/**
 * one course may need multi preCourses;
 *
 * @author liuchenguang002
 */
public class CourseSchedule_207 {

    public static void main(String[] args) {
        int[][] pre = {{1, 4}, {2, 4}, {3, 1}, {3, 2}, {5, 6}, {6, 5}};
        Solution s = new Solution();
        boolean canFinish = s.canFinish(7, pre);
        System.out.print(canFinish);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            boolean[] finished = new boolean[numCourses];
            Map<Integer, List<Integer>> preMap = new HashMap<>(numCourses);
            for (int[] pre : prerequisites) {
                preMap.putIfAbsent(pre[0], new ArrayList<>());
                preMap.get(pre[0]).add(pre[1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (finished[i]) {
                    continue;
                }

                Set<Integer> dependSet = new HashSet<>();
                dependSet.add(i);
                if (!dfs(i, preMap, dependSet, finished)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(Integer course,
                            Map<Integer, List<Integer>> preMap,
                            Set<Integer> dependSet,
                            boolean[] finished) {

            List<Integer> preList = preMap.get(course);
            if (preList != null) {
                for (Integer pre : preList) {
                    if (finished[pre]) {
                        continue;
                    }
                    if (!dependSet.add(pre)) {
                        return false;
                    }

                    if (dfs(pre, preMap, dependSet, finished)) {
                        dependSet.remove(pre);
                    } else {
                        return false;
                    }
                }
            }

            finished[course] = true;
            dependSet.remove(course);
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}