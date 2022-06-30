package cn;

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 2550 👎 0


public class ReverseLinkedList_206 {
    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        /**
         * 递归写法
         */
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            reverse(head);
            return targetHead;
        }

        private ListNode targetHead;

        /**
         * param current: 当前需要反转链表的头节点;
         * return : 反转后链表的尾节点;
         */
        private ListNode reverse(ListNode current) {
            if (current.next == null) {
                targetHead = current;
                return current;
            } else {
                ListNode tail = reverse(current.next);
                tail.next = current;
                current.next = null;
                return current;
            }
        }

        /**
         * 迭代写法：我们选择使用虚拟头节点来方便书写
         */
        private ListNode iteration(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode dummyHead = new ListNode();
            dummyHead.next = head;
            ListNode current = head.next;
            head.next = null;
            while (current != null) {
                ListNode next = current.next;
                current.next = dummyHead.next;
                dummyHead.next = current;
                current = next;
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}