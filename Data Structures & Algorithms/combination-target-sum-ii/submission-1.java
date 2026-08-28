class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] nums, int index, int sum, int target, List<Integer> sequence, List<List<Integer>> result) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(sequence);
            return;
        }

        if (index == nums.length) {
            return;
        }

        List<Integer> nextSequence = new ArrayList<>(sequence);
        nextSequence.add(nums[index]);
        helper(nums, index + 1, sum + nums[index], target, nextSequence, result);

        if (!sequence.isEmpty() && nums[index] == sequence.getLast()) {
            return;
        }
        helper(nums, index + 1, sum, target, sequence, result);
    }
}
