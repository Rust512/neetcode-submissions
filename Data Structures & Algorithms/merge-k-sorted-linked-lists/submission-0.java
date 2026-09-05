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
    private static record Pair<U, V>(U first, V second) {}

    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        PriorityQueue<Pair<Integer, Integer>> container = new PriorityQueue<>(Comparator.comparing(Pair::second));

        for (int i = 0; i < k; i++) {
            if (lists[i] == null) {
                continue;
            }
            container.offer(new Pair<>(i, lists[i].val));
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while (!container.isEmpty()) {
            Pair<Integer, Integer> removed = container.poll();
            int index = removed.first();
            current.next = lists[index];
            lists[index] = lists[index].next;
            if (lists[index] != null) {
                container.offer(new Pair<>(index, lists[index].val));
            }
            current = current.next;
        }

        return head.next;
    }
}
