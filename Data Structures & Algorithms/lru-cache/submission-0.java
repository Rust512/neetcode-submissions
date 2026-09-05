class LRUCache {

    private static class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private int size = 0;
    private final int capacity;
    private ListNode head = null;
    private ListNode tail = null;
    private final Map<Integer, ListNode> container = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!container.containsKey(key)) {
            return -1;
        }

        ListNode node = container.get(key);

        removeNode(node);
        addNodeToHead(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (container.containsKey(key)) {
            ListNode node = container.get(key);
            node.val = value;
            removeNode(node);
            addNodeToHead(node);
            container.put(key, node);
            return;
        }

        if (size == capacity) {
            int tailKey = tail.key;
            removeNode(tail);
            container.remove(tailKey);
            size--;
        }

        ListNode node = new ListNode(key, value);
        addNodeToHead(node);
        container.put(key, node);
        size++;
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        node.next = null;
        node.prev = null;
    }

    private void addNodeToHead(ListNode node) {
        node.next = head;
        if (head != null) {
            head.prev = node;
        }

        if (tail == null) {
            tail = node;
        }

        head = node;
    }
}
