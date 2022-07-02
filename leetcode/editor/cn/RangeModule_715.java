package cn;

//Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
//
// 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。 
//
// 实现 RangeModule 类: 
//
// 
// RangeModule() 初始化数据结构的对象。 
// void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的
//数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。 
// boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返
//回 true ，否则返回 false 。 
// void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入
//["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryR
//ange"]
//[[}, [10, 20}, [14, 16}, [10, 14}, [13, 15}, [16, 17]]
//输出
//[null, null, null, true, false, true]
//
//解释
//RangeModule rangeModule = new RangeModule();
//rangeModule.addRange(10, 20);
//rangeModule.removeRange(14, 16);
//rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
//rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样
//的数字）
//rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= left < right <= 109 
// 在单个测试用例中，对 addRange 、 queryRange 和 removeRange 的调用总数不超过 104 次 
// 
// Related Topics 设计 线段树 有序集合 
// 👍 165 👎 0


import java.util.Map;
import java.util.TreeMap;

public class RangeModule_715 {
    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();

//        rangeModule.addRange(6, 8);
//        rangeModule.removeRange(7, 8);
//        rangeModule.removeRange(8, 9);
//        rangeModule.addRange(8, 9);q
//        rangeModule.removeRange(1, 3);
//        rangeModule.addRange(1, 8);
//        System.out.print(rangeModule.queryRange(2, 4));
//        System.out.print(rangeModule.queryRange(2, 9));
//        System.out.print(rangeModule.queryRange(4, 6));

//        rangeModule.removeRange(4, 8);
//        rangeModule.addRange(1, 10);
//        System.out.print(rangeModule.queryRange(1, 7));
//        rangeModule.addRange(2, 3);
//        rangeModule.removeRange(2, 3);
//        System.out.print(rangeModule.queryRange(8, 9));
//        System.out.print(rangeModule.queryRange(6, 9));
//        rangeModule.addRange(2, 3);
//        rangeModule.removeRange(1, 8);

//        rangeModule.addRange(10, 180);
//        rangeModule.addRange(150, 200);
//        rangeModule.addRange(250, 500);
//        System.out.print(rangeModule.queryRange(50, 100));
//        System.out.print(rangeModule.queryRange(180, 300));
//        System.out.print(rangeModule.queryRange(600, 1000));
//        rangeModule.removeRange(50, 150);
//        System.out.print(rangeModule.queryRange(50, 100));

        String[] cmd = {"addRange", "addRange", "removeRange", "queryRange", "queryRange", "removeRange", "removeRange", "removeRange", "removeRange", "removeRange", "queryRange", "removeRange", "addRange", "removeRange", "addRange", "queryRange", "queryRange", "addRange", "addRange", "queryRange", "removeRange", "queryRange", "addRange", "queryRange", "removeRange", "removeRange", "addRange", "addRange", "removeRange", "removeRange", "removeRange", "addRange", "addRange", "queryRange", "queryRange", "queryRange", "queryRange", "queryRange", "removeRange", "removeRange", "queryRange", "addRange", "addRange", "addRange", "queryRange", "addRange", "addRange", "removeRange", "addRange", "queryRange", "removeRange", "addRange", "queryRange", "addRange", "addRange", "addRange", "queryRange", "addRange", "queryRange", "removeRange", "removeRange", "removeRange", "removeRange", "queryRange", "removeRange", "queryRange", "queryRange", "removeRange", "queryRange", "addRange", "addRange", "queryRange", "removeRange", "removeRange", "queryRange", "addRange", "removeRange", "removeRange", "addRange", "addRange", "addRange", "queryRange", "queryRange", "addRange", "queryRange", "removeRange", "queryRange", "removeRange", "addRange", "queryRange"};
        int[][] nums = {{55, 62}, {1, 29}, {18, 49}, {6, 98}, {59, 71}, {40, 45}, {4, 58}, {57, 69}, {20, 30}, {1, 40}, {73, 93}, {
                32, 93}, {38, 100}, {50, 64}, {26, 72}, {8, 74}, {15, 53}, {44, 85}, {10, 71}, {54, 70}, {10, 45}, {30, 66}, {47, 98}, {1, 7}, {
                44, 78}, {31, 49}, {62, 63}, {49, 88}, {47, 72}, {8, 50}, {49, 79}, {31, 47}, {54, 87}, {77, 78}, {59, 100}, {8, 9}, {50, 51}, {
                67, 93}, {25, 86}, {8, 92}, {31, 87}, {90, 95}, {28, 56}, {10, 42}, {27, 34}, {75, 81}, {17, 63}, {78, 90}, {9, 18}, {51, 74}, {
                20, 54}, {35, 72}, {2, 29}, {28, 41}, {17, 95}, {73, 75}, {34, 43}, {57, 96}, {51, 72}, {21, 67}, {40, 73}, {14, 26}, {
                71, 86}, {34, 41}, {10, 25}, {27, 68}, {18, 32}, {30, 31}, {45, 61}, {64, 66}, {18, 93}, {13, 21}, {13, 46}, {56, 99}, {
                6, 93}, {25, 36}, {27, 88}, {82, 83}, {30, 71}, {31, 73}, {10, 41}, {71, 72}, {9, 56}, {22, 76}, {38, 74}, {2, 77}, {33, 61}, {
                74, 75}, {11, 43}, {27, 75}};
        Boolean[] result =
                {null, null, null, false, false, null, null, null, null, null, false, null, null, null, null, false, false, null, null, true, null, false, null, false, null, null, null, null, null, null, null, null, null, true, true, false, false, true, null, null, false, null, null, null, true, null, null, null, null, false, null, null, false, null, null, null, true, null, true, null, null, null, null, false, null, false, false, null, false, null, null, false, null, null, false, null, null, null, null, null, null, true, true, null, true, null, false, null, null, false};
        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].equals("addRange")) {
                rangeModule.addRange(nums[i][0], nums[i][1]);
            } else if (cmd[i].equals("removeRange")) {
                rangeModule.removeRange(nums[i][0], nums[i][1]);
            } else if (cmd[i].equals("queryRange")) {
                System.out.print(rangeModule.queryRange(nums[i][0], nums[i][1]));
                System.out.println(result[i] + "");
            }

        }
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class RangeModule {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public RangeModule() {

        }

        public void addRange(int left, int right) {
            Map.Entry<Integer, Integer> entry;

            entry = map.floorEntry(left);
            // entry.getKey()<=left
            if (entry != null) {
                if (entry.getValue() <= right && entry.getValue() > left) {
                    map.remove(entry.getKey());
                    left = entry.getKey();
                } else if (entry.getValue() >= right) {
                    return;
                }
            }


            while ((entry = map.ceilingEntry(left)) != null && entry.getValue() <= right) {
                map.remove(entry.getKey());
            }
            if (entry != null) {
                // left <= entry.getKey() < entry.getValue()
                if (entry.getValue() > right && entry.getKey() <= right) {
                    map.remove(entry.getKey());
                    map.put(left, entry.getValue());
                } else if (right <= entry.getKey()) {
                    map.put(left, right);
                }
            } else {
                map.put(left, right);
            }

        }

        public boolean queryRange(int left, int right) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(left);
            if (entry != null) {
                return entry.getValue() >= right;
            } else {
                return false;
            }
        }

        public void removeRange(int left, int right) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(left);
            // entry.getKey() <= left < right
            if (entry != null && entry.getValue() > left) {
                map.put(entry.getKey(), left);
                if (entry.getValue() > right) {
                    map.put(right, entry.getValue());
                }
            }
            // left <= entry.getKey() < entry.getRight()
            while ((entry = map.ceilingEntry(left)) != null && entry.getValue() <= right) {
                map.remove(entry.getKey());
            }
            if (entry != null && right < entry.getValue() && entry.getKey() <= right) {
                map.remove(entry.getKey());
                map.put(right, entry.getValue());
            }
        }
    }

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}