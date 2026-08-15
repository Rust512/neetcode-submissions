class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int majority = nums[0];
        int poll = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == majority) {
                poll++;
                continue;
            }

            poll--;
            if (poll < 0) {
                majority = nums[i];
            }
        }

        return majority;
    }
}