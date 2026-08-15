class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        std::unordered_map<int, int> valueToIndexMap;
        for (int i = 0; i < (int) nums.size(); i++) {
            int toFind = target - nums[i];
            if (valueToIndexMap.find(toFind) != valueToIndexMap.end()) {
                return {valueToIndexMap[toFind], i};
            }
            valueToIndexMap[nums[i]] = i;
        }
        return {};
    }
};
