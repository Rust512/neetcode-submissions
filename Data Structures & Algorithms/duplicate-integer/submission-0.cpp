class Solution {
public:
    bool hasDuplicate(vector<int>& nums) {
        std::unordered_set<int> uniques;
        for (const int entry: nums) {
            if (uniques.find(entry) != uniques.end()) {
                return true;
            }
            uniques.insert(entry);
        }
        return false;
    }
};