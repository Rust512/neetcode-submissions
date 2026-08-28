class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> container = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            container.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] < container.peek()) {
                continue;
            }

            container.poll();
            container.offer(nums[i]);
        }

        return container.peek();
    }
}
