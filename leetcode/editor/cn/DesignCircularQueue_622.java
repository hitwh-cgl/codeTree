package cn;

//设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”
//。 
//
// 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环
//队列，我们能使用这些空间去存储新的值。 
//
// 你的实现应该支持如下操作： 
//
// 
// MyCircularQueue(k): 构造器，设置队列长度为 k 。 
// Front: 从队首获取元素。如果队列为空，返回 -1 。 
// Rear: 获取队尾元素。如果队列为空，返回 -1 。 
// enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。 
// deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。 
// isEmpty(): 检查循环队列是否为空。 
// isFull(): 检查循环队列是否已满。 
// 
//
// 
//
// 示例： 
//
// MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//circularQueue.enQueue(1);  // 返回 true
//circularQueue.enQueue(2);  // 返回 true
//circularQueue.enQueue(3);  // 返回 true
//circularQueue.enQueue(4);  // 返回 false，队列已满
//circularQueue.Rear();  // 返回 3
//circularQueue.isFull();  // 返回 true
//circularQueue.deQueue();  // 返回 true
//circularQueue.enQueue(4);  // 返回 true
//circularQueue.Rear();  // 返回 4 
//
// 
//
// 提示： 
//
// 
// 所有的值都在 0 至 1000 的范围内； 
// 操作数将在 1 至 1000 的范围内； 
// 请不要使用内置的队列库。 
// 
// Related Topics 设计 队列 数组 链表 
// 👍 337 👎 0


public class DesignCircularQueue_622 {
    public static void main(String[] args) {
        // ["MyCircularQueue","enQueue","Front","enQueue","Rear","enQueue","enQueue","Rear","deQueue","Front","deQueue","Front"]
        // [[7],[0],[],[4],[],[6],[3],[],[],[],[],[]]
        MyCircularQueue queue = new MyCircularQueue(7);
        queue.enQueue(0);
        queue.Front();
        queue.enQueue(4);
        queue.Rear();
        queue.enQueue(6);
        queue.enQueue(3);
        queue.Rear();
        queue.deQueue();
        queue.Front();
        queue.deQueue();
        queue.Front();

//        queue.isEmpty();
//        queue.Rear();
//        queue.deQueue();
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class MyCircularQueue {

        private int[] value;
        private int size;
        private int head = -1;
        private int tail = -1;

        public MyCircularQueue(int k) {
            value = new int[k];
            size = k;
        }

        public boolean enQueue(int value) {
            if (head == tail && tail == -1) {
                head = 0;
                tail = 0;
                this.value[0] = value;
                return true;
            }

            int nextInsertIndex = (tail + 1) % size;
            if (nextInsertIndex == head) {
                return false;
            } else {
                this.value[nextInsertIndex] = value;
                tail = nextInsertIndex;
                return true;
            }
        }

        public boolean deQueue() {
            if (head == tail && head == -1) {
                return false;
            }

            if (head == tail) {
                head = -1;
                tail = -1;
                return true;
            }

            head = (head + 1) % size;
            return true;
        }

        public int Front() {
            if (head != -1) {
                return value[head];
            }
            return -1;
        }

        public int Rear() {
            if (tail != -1) {
                return value[tail];
            }
            return -1;
        }

        public boolean isEmpty() {
            return head == tail && head == -1;
        }

        public boolean isFull() {
            return head == (tail + 1) % size;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}