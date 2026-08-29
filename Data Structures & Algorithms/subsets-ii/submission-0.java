class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<>());
        return result;
    }

    private void helper(int[] nums, int index, List<Integer> sequence) {
        int n = nums.length;
        if (index == n) {
            result.add(new ArrayList<>(sequence));
            return;
        }

        sequence.add(nums[index]);
        helper(nums, index + 1, sequence);
        sequence.removeLast();

        while (index + 1 < n && nums[index + 1] == nums[index]) index++;
        helper(nums, index + 1, sequence);
    }
}
