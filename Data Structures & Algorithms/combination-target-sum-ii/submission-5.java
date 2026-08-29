class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, target, 0, 0, new ArrayList<>());
        return result;        
    }

    private void helper(int[] candidates, int target, int index, int sum, List<Integer> sequence) {
        if (sum == target) {
            result.add(new ArrayList<>(sequence));
            return;
        }

        int n = candidates.length;
        if (sum > target || index == n) {
            return;
        }

        sequence.add(candidates[index]);
        helper(candidates, target, index + 1, sum + candidates[index], sequence);
        sequence.removeLast();
        while (index + 1 < n && candidates[index + 1] == candidates[index]) index++;
        helper(candidates, target, index + 1, sum, sequence);
    }
}
