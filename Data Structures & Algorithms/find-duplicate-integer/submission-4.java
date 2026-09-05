class Solution {
    public int findDuplicate(int[] nums) {
        int size = nums.length;

        for (int i = 0; i < size; i++) {
            nums[i]--;
        }

        for (int i = 0; i < size; i++) {
            int index = nums[i] % size;
            nums[index] += size;
        }

        int repeated = -1;
        for (int i = 0; i < size; i++) {
            if (nums[i] / size > 1) {
                repeated = i + 1;
            }
        }

        for (int i = 0; i < size; i++) {
            nums[i] = (nums[i] % size) + 1;
        }

        return repeated;
    }
}
