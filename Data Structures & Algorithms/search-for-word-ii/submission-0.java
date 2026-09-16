public static class PrefixTreeNode {

    private final PrefixTreeNode[] children;
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
}

public static class PrefixTree {

    private PrefixTreeNode root;
    private final int numberOfChars;
    private final char startChar;

    public PrefixTree(int numberOfChars, char startChar) {
        this.numberOfChars = numberOfChars;
        this.startChar = startChar;
        root = new PrefixTreeNode(numberOfChars);
    }

    public PrefixTree() {
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
    }

    public boolean search(String word) {
        PrefixTreeNode current = root;
        for (char letter : word.toCharArray()) {
            PrefixTreeNode child = current.getChild(getIndex(letter));
            if (child == null) {
                return false;
            }
            current = child;
        }

        return true;
    }
}

public static class WordExplorer {

    private final int rows;
    private final int cols;
    private final char[][] board;
    private final boolean[][] visited;
    private final PrefixTree prefixTree;

    private static final int DIRECTIONS = 4;
    private static final int[] ROW_DIFF = { -1, 0, 1, 0 };
    private static final int[] COL_DIFF = { 0, 1, 0, -1 };

    public WordExplorer(char[][] board) {
        assert board != null && board.length != 0 && board[0].length != 0;
        rows = board.length;
        cols = board[0].length;
        this.board = board;
        visited = new boolean[rows][cols];
        prefixTree = new PrefixTree();
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void explore() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                StringBuilder builder = new StringBuilder();
                visited[r][c] = true;
                explore(r, c, builder);
                visited[r][c] = false;
            }
        }
    }

    private void explore(int row, int col, StringBuilder builder) {
        for (int dir = 0; dir < DIRECTIONS; dir++) {
            int newRow = row + ROW_DIFF[dir];
            int newCol = col + COL_DIFF[dir];
            if (!valid(newRow, newCol) || visited[newRow][newCol]) continue;

            visited[newRow][newCol] = true;
            builder.append(board[row][col]);
            explore(newRow, newCol, builder);
            builder.setLength(builder.length() - 1);
            visited[newRow][newCol] = false;
        }

        prefixTree.addWord(builder.toString());
    }

    public boolean searchWord(String word) {
        return prefixTree.search(word);
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        WordExplorer explorer = new WordExplorer(board);
        explorer.explore();
        return Arrays.stream(words)
            .filter(word -> explorer.searchWord(word))
            .collect(Collectors.toList());
    }
}
