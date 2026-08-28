class KthLargest {
    private final PriorityQueue<Integer> container = new PriorityQueue<Integer>();

    public KthLargest(int k, int[] nums) {
        for (int i = 0; i < k; i++) {
            container.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            processValue(nums[i]);
        }
    }

    private void processValue(int value) {
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
