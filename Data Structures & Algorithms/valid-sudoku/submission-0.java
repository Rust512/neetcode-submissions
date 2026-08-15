class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] rowCounter = new int[9][9];
        int[][] columnCounter = new int[9][9];
        int[][] boxCounter = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int value = (board[i][j] - '0');
                rowCounter[i][value - 1]++;
                columnCounter[j][value - 1]++;
                int boxRow = i / 3;
                int boxColumn = j / 3;
                int boxIndex = 3 * boxRow + boxColumn;
                boxCounter[boxIndex][value - 1]++;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (rowCounter[i][j] > 1 || columnCounter[i][j] > 1 || boxCounter[i][j] > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
