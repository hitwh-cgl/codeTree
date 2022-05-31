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
        public ListNode reverseList(ListNode head) {
            return recursion(head);
        }

        private ListNode recursion(ListNode head) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return head;
            }
            ListNode targetHead = reverseList(head.next);
            ListNode curTail = targetHead;
            while (curTail.next != null) {
                curTail = curTail.next;
            }
            curTail.next = head;
            head.next = null;
            return targetHead;
        }

        private ListNode iteration(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode sourceNext = head.next;
            ListNode targetHead = head;
            while (sourceNext != null) {
                ListNode cur = sourceNext;
                sourceNext = sourceNext.next;
                cur.next = targetHead;
                targetHead = cur;
            }
            // 防止循环
            head.next = null;
            return targetHead;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}