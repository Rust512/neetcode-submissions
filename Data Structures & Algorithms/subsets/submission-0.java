class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void helper(int[] nums, int index, List<Integer> subset, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(subset);
            return;
        }

        helper(nums, index + 1, subset, result);
        List<Integer> nextSubset = new ArrayList<>(subset);
        nextSubset.add(nums[index]);
        helper(nums, index + 1, nextSubset, result);
    }
}
