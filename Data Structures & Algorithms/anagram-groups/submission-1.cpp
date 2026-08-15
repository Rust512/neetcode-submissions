#include <array>

class Solution {
public:
    int getIndex(char letter) {
        return letter - 'a';
    }

    char getLetter(int index) {
        return 'a' + index;
    }

    std::string runLengthEncoding(const std::string word) {
        std::array<int, 26> frequency{0};
        std::string key = "";

        for (const char letter: word) {
            frequency[getIndex(letter)]++;
        }

        // create key using run-length encoding
        for (int i = 0; i < 26; i++) {
            key.push_back(getLetter(i));
            key.append(std::to_string(frequency[i]));
        }

        return key;
    }

    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        std::unordered_map<std::string, std::vector<std::string>> groups;
        for (int i = 0; i < (int) strs.size(); i++) {
            std::string key = runLengthEncoding(strs[i]);
            if (groups.find(key) != groups.end()) {
                groups[key].emplace_back(strs[i]);
                continue;
            }
            std::vector<std::string> initialGroup;
            initialGroup.emplace_back(strs[i]);
            groups.insert({key, initialGroup});
        }

        std::vector<std::vector<std::string>> listOfGroups;
        for (const auto pair: groups) {
            listOfGroups.emplace_back(pair.second);
        }

        return listOfGroups;
    }
};
