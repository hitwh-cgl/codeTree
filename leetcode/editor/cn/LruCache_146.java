package cn;

//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。 
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int hashMap) 如果关键字 key 已经存在，则变更其数据值 hashMap ；如果不存在，则向缓存中插入该组 ke
//y-hashMap 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= hashMap <= 105
// 最多调用 2 * 105 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1967 👎 0


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 也不是不能解决，就是性能太低了，捂脸
 * 用LinkedHashMap可以偷懒，性能会好一点，但是也需要自己解决重复put和get提高优先级的问题
 */
public class LruCache_146 {

    //leetcode submit region begin(Prohibit modification and deletion)

    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super();
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = super.get(key);
            if (value == null) {
                return -1;
            } else {
                super.remove(key);
                super.put(key, value);
                return value;
            }
        }

        public void put(int key, int value) {
            if (super.containsKey(key)) {
                super.remove(key);
            }
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() > capacity;
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,hashMap);
     */
//leetcode submit region end(Prohibit modification and deletion)

    class LRUCache2 {
        private HashMap<Integer, Integer> hash;
        private HashMap<Integer, Integer> keyToNo;
        private TreeMap<Integer, Integer> noToKey;
        private int no;
        private int capacity;

        public LRUCache2(int capacity) {
            hash = new HashMap<>();
            keyToNo = new HashMap<>();
            noToKey = new TreeMap<>();
            no = 0;
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer result = hash.get(key);
            if (result != null) {
                Integer curNo = keyToNo.get(key);
                noToKey.remove(curNo);
                no++;
                noToKey.put(no, key);
                keyToNo.put(key, no);
                return result;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (hash.containsKey(key)) {
                Integer curNo = keyToNo.get(key);
                noToKey.remove(curNo);
                keyToNo.remove(key);
                no++;
                hash.put(key, value);
                noToKey.put(no, key);
                keyToNo.put(key, no);
            } else if (hash.size() >= capacity) {
                Map.Entry<Integer, Integer> first = noToKey.pollFirstEntry();
                hash.remove(first.getValue());
                keyToNo.remove(first.getValue());
                hash.put(key, value);
                no++;
                noToKey.put(no, key);
                keyToNo.put(key, no);
            } else {
                hash.put(key, value);
                no++;
                keyToNo.put(key, no);
                noToKey.put(no, key);

            }
        }
    }
}