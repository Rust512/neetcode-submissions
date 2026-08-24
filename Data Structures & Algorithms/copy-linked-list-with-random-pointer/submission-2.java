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
        // create a copy with only the next links.
        Node dummyHead = new Node(0);
        Node copyCurr = dummyHead;
        Node curr = head;

        while (curr != null) {
            // initialize new node
            Node newNode = new Node(curr.val);
            newNode.random = curr;

            // assign new node
            copyCurr.next = newNode;

            // update iterator
            copyCurr = copyCurr.next;
            curr = curr.next;
        }

        Node copyHead = dummyHead.next;

        // create another copy, and such that the next pointers in the original list point to the corresponding nodes in this new copy.
        dummyHead = new Node(0);
        Node resultCurr = dummyHead;
        curr = head;

        while (curr != null) {
            // initialize new node
            Node newNode = new Node(curr.val);
            newNode.random = curr;

            // assign new node
            Node next = curr.next;
            curr.next = newNode;
            resultCurr.next = newNode;

            // update iterator
            resultCurr = resultCurr.next;
            curr = next;
        }

        Node resultHead = dummyHead.next;

        // Initialize the random pointers in the result copy.
            
        curr = resultHead;
        while (curr != null) {
            Node originalNode = curr.random;
            curr.random = (originalNode.random != null) ? originalNode.random.next : null;
            curr = curr.next;
        }

        // restore the original list using the copy.
        curr = copyHead;
        while (curr != null) {
            Node originalNode = curr.random;
            originalNode.next = (curr.next != null) ? curr.next.random : null;
            curr = curr.next;
        }

        return resultHead;
    }
}
