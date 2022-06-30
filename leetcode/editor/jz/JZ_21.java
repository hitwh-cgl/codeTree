package jz;


import java.util.ArrayDeque;
import java.util.Deque;

public class JZ_21 {
    class Solution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            Deque<ListNode> queue = new ArrayDeque<>(k);
            ListNode current = head;
            while (current != null) {
                queue.addLast(current);
                current = current.next;
                if (queue.size() > k) {
                    queue.removeFirst();
                }
            }
            return queue.removeFirst();
        }
    }
}
