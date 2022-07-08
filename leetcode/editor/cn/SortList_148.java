package cn;

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 1669 👎 0


/**
 * 最快的排序策略是先拷贝值到数组，然后Arrays.sort再复制回去；
 * 因为即使我传递长度给后续函数，拆分数组时仍然需要多一次遍历，整体耗时确实类似2倍的关系；
 * 注意归并排序的递归结束条件，避免栈溢出；
 *
 * @author 17862
 */
public class SortList_148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        Solution solution = new Solution();
        ListNode result = solution.sortList(head);
        System.out.println(result.val);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 类似归并排序
         */
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }

            int count = 0;
            ListNode node = head;
            while (node != null) {
                count++;
                node = node.next;
            }

            if (count == 1) {
                return head;
            } else {
                return sortList(head, count);
            }
        }

        private ListNode sortList(ListNode head, int count) {
            if (count == 1) {
                return head;
            }

            ListNode cur = head;
            int curLength = 1;
            int length = count / 2;
            while (curLength < length) {
                cur = cur.next;
                curLength++;
            }
            ListNode head2 = cur.next;
            cur.next = null;
            ListNode head1 = sortList(head, curLength);
            head2 = sortList(head2, count - curLength);
            return mergeList(head1, head2);
        }

        private ListNode mergeList(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode();
            ListNode cur = dummyHead;
            while (head1 != null && head2 != null) {
                if (head1.val <= head2.val) {
                    cur.next = new ListNode(head1.val);
                    head1 = head1.next;
                } else {
                    cur.next = new ListNode(head2.val);
                    head2 = head2.next;
                }
                cur = cur.next;
            }
            cur.next = head1 == null ? head2 : head1;
            return dummyHead.next;
        }

        /**
         * 类似插入排序，超时
         */
        public ListNode insertSort(ListNode head) {
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = new ListNode(head.val);
            for (ListNode cur = head.next; cur != null; cur = cur.next) {
                for (ListNode pre = dummyHead; true; pre = pre.next) {
                    if (pre.next == null) {
                        pre.next = new ListNode(cur.val);
                        break;
                    } else if (pre.next.val >= cur.val) {
                        pre.next = new ListNode(cur.val, pre.next);
                        break;
                    }
                }
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}