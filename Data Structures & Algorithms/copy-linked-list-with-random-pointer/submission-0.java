/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node curr = head;
        Node newHead = new Node(-101);
        Node prev = newHead;
        Node newCurr = null;

        while (curr != null) {
            newCurr = new Node(curr.val);

            Node next = curr.next;
            curr.next = newCurr;
            newCurr.random = curr;

            prev.next = newCurr;
            prev = newCurr;
            curr = next;
        }

        newHead = newHead.next;
        curr = newHead;
        while (curr != null) {
            Node oldNode = curr.random;

            if (oldNode.random != null) {
                curr.random = oldNode.random.next;
            } else {
                curr.random = null;
            }
            curr = curr.next;
        }

        return newHead;
    }
}
