class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        return Math.max(rob(nums, 1, n - 1), rob(nums, 0, n - 2));
    }

    private int rob(int[] nums, int start, int end) {
        assert start >= 0 && start < end && end < nums.length;
        int a = 0;
        int b = nums[start];

        for (int i = start + 1; i <= end; i++) {
            int curr = Math.max(a + nums[i], b);
            a = b;
            b = curr;
        }

        return b;
    }
}
