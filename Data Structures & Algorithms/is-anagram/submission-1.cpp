class Solution {
public:
    bool isAnagram(string s, string t) {
        std::vector<int> charCountForS(26, 0);
        for (const char letter: s) {
            charCountForS[letter - 'a']++;
        }

        for (const char letter: t) {
            if (charCountForS[letter - 'a'] == 0) {
                return false;
            }
            charCountForS[letter - 'a']--;
        }

        for (const int count: charCountForS) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }
};
