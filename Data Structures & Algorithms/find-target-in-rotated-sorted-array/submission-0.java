class Solution {
    public int search(int[] nums, int target) {
        int size = nums.length;
        int minIndex = findMinIndex(nums);
        int start = 0;
        int end = size - 1;
        if (target <= nums[end]) {
            start = minIndex;
        } else if (target >= nums[start]) {
            end = (size + minIndex - 1) % size;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int val = nums[mid];
            if (target == val) {
                return mid;
            }

            if (target < val) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return -1;
    }

    private int findMinIndex(int[] nums) {
        int size = nums.length;
        int minIndex = 0;
        int start = 0;
        int end = size - 1;

        if (nums[start] <= nums[end]) {
            return start;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int val = nums[mid];

            if (val < nums[start]) {
                minIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return minIndex;
    }
}
