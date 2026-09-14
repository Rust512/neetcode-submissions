public static class PrefixTreeNode {

    private final PrefixTreeNode[] children;
    private boolean end = false;

    private final int numberOfChars;

    public PrefixTreeNode(int numberOfChars) {
        this.children = new PrefixTreeNode[numberOfChars];
        this.numberOfChars = numberOfChars;
    }

    public PrefixTreeNode getChild(int index) {
        assert index >= 0 && index < numberOfChars;
        return children[index];
    }

    public PrefixTreeNode[] getChildren() {
        return children;
    }

    public void setChild(int index, PrefixTreeNode node) {
        assert index >= 0 && index < numberOfChars;
        children[index] = node;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEnd() {
        return end;
    }
}

class WordDictionary {
    private PrefixTreeNode root;
    private final int numberOfChars;
    private final char startChar;

    public WordDictionary(int numberOfChars, char startChar) {
        this.numberOfChars = numberOfChars;
        this.startChar = startChar;
        root = new PrefixTreeNode(numberOfChars);
    }

    public WordDictionary() {
        this(26, 'a');
    }

    private char getLastChar() {
        return (char) (startChar + numberOfChars - 1);
    }

    private int getIndex(char c) {
        assert c >= startChar && c <= getLastChar();
        return c - startChar;
    }

    private PrefixTreeNode newNode() {
        return new PrefixTreeNode(numberOfChars);
    }

    public void addWord(String word) {
        PrefixTreeNode current = root;
        for (char letter : word.toCharArray()) {
            int index = getIndex(letter);
            if (current.getChild(index) == null) {
                current.setChild(index, newNode());
            }

            current = current.getChild(index);
        }

        current.setEnd(true);
    }

    public boolean search(String word) {
        return search(root, 0, word);
    }

    public boolean search(PrefixTreeNode node, int index, String word) {
        if (index == word.length()) {
            return node.isEnd();
        }

        char letter = word.charAt(index);

        if (letter != '.') {
            int charIndex = getIndex(letter);
            PrefixTreeNode current = node.getChild(charIndex);

            if (current == null) {
                return false;
            }

            return search(current, index + 1, word);
        }

        for (PrefixTreeNode child : node.getChildren()) {
            if (child == null) {
                continue;
            }
            if (search(child, index + 1, word)) {
                return true;
            }
        }

        return false;
    }
}
