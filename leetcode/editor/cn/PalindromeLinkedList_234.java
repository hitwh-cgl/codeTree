package cn;

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 105] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 
// 👍 1385 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PalindromeLinkedList_234 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        /**
         * 使用栈
         */
        public boolean isPalindrome(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode point = head;
            while (point != null) {
                stack.push(point.val);
                point = point.next;
            }

            while (head != null) {
                if (stack.peek() == head.val) {
                    stack.pop();
                } else {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        /**
         * 复制到数组后使用双指针
         */
        public boolean isPalindromeUseArray(ListNode head) {
            List<Integer> list = new ArrayList<>();
            ListNode point = head;
            while (point != null) {
                list.add(point.val);
                point = point.next;
            }

            int left = 0, right = list.size() - 1;
            while (left < right) {
                if (!list.get(left).equals(list.get(right))) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}