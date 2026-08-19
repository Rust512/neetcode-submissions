class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        if (nums[0] <= nums[end]) {
            return nums[0];
        }

        int min = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int value = nums[mid];

            if (value < nums[0]) {
                min = value;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return min;
    }
}
