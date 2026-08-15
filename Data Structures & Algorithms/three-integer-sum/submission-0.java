class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (!result.isEmpty() && result.get(result.size() - 1).get(0).equals(nums[i])) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == -nums[i]) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    do {left++;} while (nums[left] == nums[left - 1] && left < right);
                    do {right--;} while (nums[right] == nums[right + 1] && left < right);
                } else if (sum < -nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
