#include <array>

class TrieNode {
public:
    static constexpr int SIZE = 26;
    static constexpr char FIRST_LETTER = 'a';

private:
    int count;
    bool end;
    std::array<TrieNode *, SIZE> children{};

public:
    TrieNode();

    ~TrieNode();

    [[nodiscard]] int getCount() const;

    void setCount(int);

    [[nodiscard]] bool isEnd() const;

    void setEnd(bool);

    [[nodiscard]] TrieNode *getChildNode(char) const;

    void setChildNode(char, TrieNode *);

    static bool validCharacter(char);

    [[nodiscard]] int getCommonLetter() const;
};

class Trie {
    TrieNode *root;

public:
    Trie();

    ~Trie();

    void insertWord(const std::string &) const;

    [[nodiscard]] std::string longestCommonPrefix() const;
};

TrieNode::TrieNode() {
    count = 0;
    end = false;
    for (int i = 0; i < SIZE; i++) {
        children[i] = nullptr;
    }
}

TrieNode::~TrieNode() {
    for (int i = 0; i < SIZE; i++) {
        if (children[i] == nullptr) continue;
        delete children[i];
    }
}

int TrieNode::getCount() const {
    return count;
}

void TrieNode::setCount(const int chartCount) {
    count = chartCount;
}

bool TrieNode::isEnd() const {
    return end;
}

void TrieNode::setEnd(const bool wordEnd) {
    end = wordEnd;
}

TrieNode *TrieNode::getChildNode(const char ch) const {
    return children[ch - FIRST_LETTER];
}

void TrieNode::setChildNode(const char ch, TrieNode *node) {
    children[ch - FIRST_LETTER] = node;
}

bool TrieNode::validCharacter(const char ch) {
    const int index = ch - FIRST_LETTER;
    return ch >= FIRST_LETTER && index >= 0 && index < SIZE;
}

int TrieNode::getCommonLetter() const {
    int commonLetterIndex = -1;
    for (int i = 0; i < SIZE; i++) {
        if (children[i] == nullptr) continue;

        if (commonLetterIndex == -1) {
            commonLetterIndex = i;
        } else {
            return -1;
        }
    }

    return commonLetterIndex;
}

Trie::Trie() {
    root = new TrieNode();
}

Trie::~Trie() {
    delete root;
}

void Trie::insertWord(const std::string &word) const {
    root->setCount(root->getCount() + 1);

    auto currentNode = root;
    for (const auto letter: word) {
        if (currentNode->getChildNode(letter) == nullptr) {
            const auto newNode = new TrieNode();
            currentNode->setChildNode(letter, newNode);
        }
        currentNode = currentNode->getChildNode(letter);
        currentNode->setCount(currentNode->getCount() + 1);
    }

    currentNode->setEnd(true);
}

std::string Trie::longestCommonPrefix() const {
    auto currentNode = root;
    int index = currentNode->getCommonLetter();
    std::string commonPrefix;
    while (index != -1 && !currentNode->isEnd()) {
        const char letter = static_cast<char>(TrieNode::FIRST_LETTER + index);
        commonPrefix += letter;
        const auto childNode = currentNode->getChildNode(letter);
        index = childNode->getCommonLetter();
        currentNode = childNode;
    }

    return commonPrefix;
}

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        const auto trie = new Trie();
        for (const auto& word: strs) {
            trie->insertWord(word);
        }

        std::string commonPrefix = trie->longestCommonPrefix();
        delete trie;

        return commonPrefix;
    }
};