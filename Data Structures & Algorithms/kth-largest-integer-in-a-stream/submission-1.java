class KthLargest {
    private final PriorityQueue<Integer> container = new PriorityQueue<Integer>();
    private final int limit;

    public KthLargest(int k, int[] nums) {
        this.limit = k;
        for (int i = 0; i < nums.length; i++) {
            processValue(nums[i]);
        }
    }

    private void processValue(int value) {
        if (container.size() < this.limit) {
            container.offer(value);
            return;
        }

        if (value <= container.peek()) {
            return;
        }

        container.poll();
        container.offer(value);
    }
    
    public int add(int val) {
        processValue(val);
        return container.peek();
    }
}
