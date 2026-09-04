class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = 0;
        int fast = 1;

        while (nums[slow] != nums[fast]) {
            slow = (slow + 1) % n;
            fast = (fast + 2) % n;
            if (slow == fast) {
                fast = (fast + 1) % n;
            }
        }

        return nums[slow];
    }
}
