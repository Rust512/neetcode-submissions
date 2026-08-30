class Solution {
    private static List<List<String>> boards = new ArrayList<>();
    private static final char EMPTY = '.';
    private static final char OCCUPIED = 'Q';

    private static class ChessBoard {
        private final int size;
        private final StringBuilder[] board;

        ChessBoard(int size) {
            this.size = size;
            board = new StringBuilder[size];

            for (int i = 0; i < size; i++) {
                board[i] = new StringBuilder();
                for (int j = 0; j < size; j++) {
                    board[i].append(EMPTY);
                }
            }
        }

        private boolean validCell(int row, int col) {
            return row >= 0 && row < size && col >= 0 && col < size;
        }

        boolean queenPlaceable(int row, int col) {
            if (!validCell(row, col)) {
                return false;
            }

            int west = col;
            int east = col;
            for (int r = row - 1; r >= 0; r--) {
                if (getEntry(r, col) == OCCUPIED || getEntry(r, --west) == OCCUPIED || getEntry(r, ++east) == OCCUPIED) {
                    return false;
                }
            }

            return true;
        }

        private char getEntry(int row, int col) {
            if (!validCell(row, col)) {
                return '\0';
            }

            return board[row].charAt(col);
        }

        void setCell(int row, int col, char val) {
            if (!validCell(row, col)) {
                return;
            }
            board[row].setCharAt(col, val);
        }

        List<String> getBoard() {
            return Arrays.stream(board)
                    .map(StringBuilder::toString)
                    .toList();
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        boards = new ArrayList<>();
        ChessBoard board = new ChessBoard(n);
        helper(n, 0, board);
        return boards;
    }

    private void helper(int n, int row, ChessBoard board) {
        if (row == n) {
            boards.add(board.getBoard());                    
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!board.queenPlaceable(row, col)) {
                continue;
            }
            board.setCell(row, col, OCCUPIED);
            helper(n, row + 1, board);
            board.setCell(row, col, EMPTY);
        }
    }
}
