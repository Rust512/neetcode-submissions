class Solution {
public:
    vector<int> getConcatenation(vector<int>& nums) {
        int n = (int) nums.size();
        auto result = std::vector<int>(2 * n);

        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[i + n] = nums[i];
        }

        return result;
    }
};