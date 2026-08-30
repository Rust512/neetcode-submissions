class PrefixTree {

    private static class PrefixTreeNode {
        private boolean end;
        private PrefixTreeNode[] children;

        private static final int SIZE = 26;
        private static final char START_CHAR = 'a';

        private PrefixTreeNode() {
            end = false;
            children = new PrefixTreeNode[SIZE];
        }

        private int getIndex(char ch) {
            return ch - START_CHAR;
        }

        private char getChar(int index) {
            return (char) (index + START_CHAR);
        }

        private PrefixTreeNode getChild(char letter) {
            int index = getIndex(letter);
            if (index < 0 || index >= SIZE) {
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
    
    private final PrefixTreeNode root;

    public PrefixTree() {
        this.root = new PrefixTreeNode();
    }

    public void insert(String word) {
        PrefixTreeNode current = root;
        for (char letter : word.toCharArray()) {
            if (current.getChild(letter) == null) {
                current.setChild(letter, new PrefixTreeNode());
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
