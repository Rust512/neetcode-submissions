class Solution {
    private static class Board {
        private final char[][] board;
        private final boolean[][] visited;
        private final int rows;
        private final int cols;

        private static final int DIRECTIONS = 4;
        private static final int[] ROW_DIFF = {-1, 0, 1, 0};
        private static final int[] COL_DIFF = {0, 1, 0, -1};

        Board(char[][] board) {
            assert board.length != 0;
            this.board = board;
            rows = board.length;
            cols = board[0].length;
            visited = new boolean[rows][cols];
        }

        boolean isValid(int row, int col) {
            return row >= 0 && row < rows && col >= 0 && col < cols;
        }

        boolean wordExists(String word) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    visited[r][c] = true;
                    if (wordExists(r, c, 0, word)) {
                        return true;
                    }
                    visited[r][c] = false;
                }
            }

            return false;
        }

        boolean wordExists(int row, int col, int index, String word) {
            if (board[row][col] != word.charAt(index)) {
                return false;
            }

            if (index == word.length() - 1) {
                return true;
            }

            boolean result = false;
            for (int d = 0; d < DIRECTIONS; d++) {
                int nextRow = row + ROW_DIFF[d];
                int nextCol = col + COL_DIFF[d];
                if (!isValid(nextRow, nextCol) || visited[nextRow][nextCol]) {continue;}
                visited[nextRow][nextCol] = true;
                result = result || wordExists(nextRow, nextCol, index + 1, word);
                visited[nextRow][nextCol] = false;
            }

            return result;
        }
    }

    public boolean exist(char[][] board, String word) {
        Board b = new Board(board);
        return b.wordExists(word);
    }
}
