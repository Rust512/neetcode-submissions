#include <array>

class Solution {
public:
    int getIndex(char letter) {
        return letter - 'a';
    }

    char getLetter(int index) {
        return 'a' + index;
    }

    std::string getKey(const std::string word) {
        std::array<int, 26> frequency{0};
        std::string key = "";

        for (const char letter: word) {
            frequency[getIndex(letter)]++;
        }

        for (int i = 0; i < 26; i++) {
            if (frequency[i] == 0) continue;
            key.append(frequency[i], getLetter(i));
        }

        return key;
    }

    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        std::unordered_map<std::string, std::vector<std::string>> groups;
        for (const std::string word: strs) {
            std::string key = getKey(word);
            if (groups.find(key) != groups.end()) {
                groups[key].emplace_back(word);
                continue;
            }
            std::vector<std::string> newList;
            newList.emplace_back(word);
            groups.insert({key, newList});
        }

        std::vector<std::vector<std::string>> listOfGroups;
        for (const auto pair: groups) {
            listOfGroups.emplace_back(pair.second);
        }

        return listOfGroups;
    }
};
