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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head;
        while (n > 0) {
            end = end.next;
            n--;
        }

        if (end == null) {
            head = head.next;
            return head;
        }

        ListNode start = head;
        while (end.next != null) {
            start = start.next;
            end = end.next;
        }

        ListNode temp = start.next;
        start.next = temp.next;
        temp.next = null;

        return head;
    }
}
