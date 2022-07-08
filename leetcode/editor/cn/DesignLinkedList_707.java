package cn;

//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
///引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。 
//
// 在链表类中实现这些功能： 
//
// 
// get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。 
// addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。 
// addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。 
// addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
//到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。 
// deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。 
// 
//
// 
//
// 示例： 
//
// MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
// 
//
// 
//
// 提示： 
//
// 
// 所有val值都在 [1, 1000] 之内。 
// 操作次数将在 [1, 1000] 之内。 
// 请不要使用内置的 LinkedList 库。 
// 
// Related Topics 设计 链表 
// 👍 489 👎 0


public class DesignLinkedList_707 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class MyLinkedList {

        public static class Node {
            private Node next;
            private int val;

            public Node() {

            }

            public Node(int value) {
                this.val = value;
            }
        }

        private Node dummyHead;
        private Node tail;

        public MyLinkedList() {
            dummyHead = new Node();
            tail = dummyHead;
        }

        public int get(int index) {
            Node current = dummyHead;
            while (index >= 0) {
                if (current.next == null) {
                    return -1;
                } else {
                    current = current.next;
                    index--;
                }
            }
            return current.val;
        }

        public void addAtHead(int val) {
            Node insert = new Node(val);
            insert.next = dummyHead.next;
            dummyHead.next = insert;
            if (tail.next != null) {
                tail = tail.next;
            }
        }

        public void addAtTail(int val) {
            tail.next = new Node(val);
            tail = tail.next;
        }

        public void addAtIndex(int index, int val) {
            Node current = dummyHead;
            while (index > 0) {
                if (current.next == null) {
                    return;
                } else {
                    current = current.next;
                    index--;
                }
            }

            Node insert = new Node(val);
            insert.next = current.next;
            current.next = insert;

            if (tail.next != null) {
                tail = tail.next;
            }
        }

        public void deleteAtIndex(int index) {
            if (dummyHead.next == null) {
                return;
            }

            Node pre = dummyHead;
            while (index > 0) {
                if (pre.next == null) {
                    return;
                } else {
                    pre = pre.next;
                    index--;
                }
            }
            if (pre.next != null) {
                if (pre.next == tail) {
                    tail = pre;
                    pre.next = null;
                } else {
                    pre.next = pre.next.next;
                }
            }
        }
    }
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

}