class PrefixTree {

    private static class PrefixTreeNode {
        private final int totalChars;
        private final char startChar;
        private boolean end;
        private PrefixTreeNode[] children;

        private PrefixTreeNode(int totalChars, char startChar) {
            this.totalChars = totalChars;
            this.startChar = startChar;
            end = false;
            children = new PrefixTreeNode[totalChars];
        }

        private int getIndex(char ch) {
            return ch - startChar;
        }

        private char getChar(int index) {
            return (char) (index + startChar);
        }

        private PrefixTreeNode getChild(char letter) {
            int index = getIndex(letter);
            if (index < 0 || index >= totalChars) {
                return null;
            }

            return children[index];
        }

        private void setChild(char letter, PrefixTreeNode child) {
            int index = getIndex(letter);
            children[index] = child;
        }

        private boolean isEnd() {
            return this.end;
        }

        private void setEnd(boolean end) {
            this.end = end;
        }
    }
    
    private final int totalChars;
    private final char startChar;
    private final PrefixTreeNode root;

    public PrefixTree(int totalChars, char startChar) {
        this.totalChars = totalChars;
        this.startChar = startChar;
        this.root = getBlankNode();
    }

    public PrefixTree() {
        this.totalChars = 26;
        this.startChar = 'a';
        this.root = getBlankNode();
    }

    private PrefixTreeNode getBlankNode() {
        return new PrefixTreeNode(totalChars, startChar);
    }

    public void insert(String word) {
        PrefixTreeNode current = root;
        for (char letter : word.toCharArray()) {
            if (current.getChild(letter) == null) {
                current.setChild(letter, getBlankNode());
            }
            current = current.getChild(letter);
        }

        current.setEnd(true);
    }

    public boolean search(String word) {
        PrefixTreeNode current = root;
        for (char letter : word.toCharArray()) {
            PrefixTreeNode child = current.getChild(letter);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return current.isEnd();
    }

    public boolean startsWith(String prefix) {
        PrefixTreeNode current = root;
        for (char letter : prefix.toCharArray()) {
            PrefixTreeNode child = current.getChild(letter);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return true;
    }
}
