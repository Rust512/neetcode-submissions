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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            current.next = new ListNode(val);
            current = current.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            l1 = l1.next;
            current.next = new ListNode(val);
            current = current.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            l2 = l2.next;
            current.next = new ListNode(val);
            current = current.next;
        }

        if (carry != 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
    }
}
