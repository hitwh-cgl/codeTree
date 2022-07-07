package cn;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 2113 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveNthNodeFromEndOfList_19 {
    static //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            Deque<ListNode> deque = new ArrayDeque<>(n);
            ListNode dummyHead = new ListNode();
            dummyHead.next = head;
            ListNode cur = dummyHead;
            while (cur != null) {
                deque.offer(cur);
                cur = cur.next;
                if (deque.size() > n + 1) {
                    deque.poll();
                }
            }
            ListNode pre = deque.poll();
            pre.next = pre.next.next;
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}