class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] leftRunningProduct = new int[nums.length];
        leftRunningProduct[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            leftRunningProduct[i] = nums[i] * leftRunningProduct[i - 1];
        }

        leftRunningProduct[nums.length - 1] = 1;

        int[] rightRunningProduct = new int[nums.length];
        rightRunningProduct[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rightRunningProduct[i] = nums[i] * rightRunningProduct[i + 1];
        }

        rightRunningProduct[0] = 1;

        int[] result = new int[nums.length];
        result[0] = rightRunningProduct[1];
        result[nums.length - 1] = leftRunningProduct[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            result[i] = leftRunningProduct[i - 1] * rightRunningProduct[i + 1];
        }

        return result;
    }
}  
