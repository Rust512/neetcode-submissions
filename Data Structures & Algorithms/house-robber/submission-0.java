class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        int a = 0;
        int b = nums[0];
        
        for (int i = 1; i < n; i++) {
            int curr = Math.max(a + nums[i], b);
            a = b;
            b = curr;
        }

        return b;
    }
}
