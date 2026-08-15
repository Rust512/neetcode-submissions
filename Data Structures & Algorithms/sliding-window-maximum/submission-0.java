class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> container = new TreeMap<>();

        for (int i = 0; i < k; i++) {
            container.merge(
                nums[i],
                1,
                (oldValue, newValue) -> oldValue + 1
            );
        }

        int n = nums.length;

        int[] result = new int[n - k + 1];
        result[0] = container.lastKey();

        int start = 0;
        for (int end = k; end < n; end++) {
            container.put(nums[start], container.get(nums[start]) - 1);
            if (container.get(nums[start]) == 0) {
                container.remove(nums[start]);
            }
            start++;
            container.merge(nums[end], 1, (oldValue, newValue) -> oldValue + 1);
            result[start] = container.lastKey();
        }

        return result;
    }
}
