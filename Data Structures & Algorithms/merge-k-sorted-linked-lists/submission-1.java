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
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        PriorityQueue<Integer> container = new PriorityQueue<>((a, b) -> lists[a].val - lists[b].val);

        for (int i = 0; i < k; i++) {
            if (lists[i] == null) {
                continue;
            }
            container.offer(i);
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while (!container.isEmpty()) {
            int index = container.poll();
            current.next = lists[index];
            lists[index] = lists[index].next;
            if (lists[index] != null) {
                container.offer(index);
            }
            current = current.next;
        }

        return head.next;
    }
}
