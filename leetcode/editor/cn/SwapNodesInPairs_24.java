package cn;

//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 递归 链表 
// 👍 1453 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class SwapNodesInPairs_24 {
    static //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode second = head.next;
            if (second == null) {
                return head;
            }

            ListNode third = swapPairs(second.next);
            second.next = head;
            head.next = third;
            return second;
        }

        /**
         * 总感觉用队列处理链表有点心虚，但是写法其实简单自然，又方便理解；
         */
        public ListNode arrayDeque(ListNode head) {
            if (head == null) {
                return null;
            }

            Deque<ListNode> deque = new ArrayDeque<>();
            while (head != null) {
                if (head.next != null) {
                    deque.offer(head.next);
                    deque.offer(head);
                    head = head.next.next;
                } else {
                    deque.offer(head);
                    head = head.next;
                }
            }
            ListNode result = deque.peek();
            ListNode pre = deque.poll();
            while (!deque.isEmpty()) {
                pre.next = deque.poll();
                pre = pre.next;
            }
            pre.next = null;
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}