package cn;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 8129 👎 0


public class AddTwoNumbers_2 {
    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode();
            result.val = (l1.val + l2.val) % 10;
            int flag = (l1.val + l2.val) / 10;
            ListNode cur = result;
            for (ListNode head1 = l1.next, head2 = l2.next; head1 != null || head2 != null; ) {
                int count = 0;
                if (head1 != null) {
                    count += head1.val;
                    head1 = head1.next;
                }
                if (head2 != null) {
                    count += head2.val;
                    head2 = head2.next;
                }
                count += flag;
                ListNode next = new ListNode(count % 10);
                cur.next = next;
                cur = next;
                flag = count / 10;
            }
            if (flag != 0) {
                cur.next = new ListNode(flag);
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}