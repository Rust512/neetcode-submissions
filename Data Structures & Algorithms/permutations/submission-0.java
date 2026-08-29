class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, result);
        return result;
    }

    private void helper(int[] nums, int startIndex, List<List<Integer>> result) {
        int n = nums.length;
        if (startIndex == n - 1) {
            result.add(IntStream.of(nums).boxed().toList());
        }

        for (int i = startIndex; i < n; i++) {
            swap(nums, startIndex, i);
            helper(nums, startIndex + 1, result);
            swap(nums, startIndex, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
