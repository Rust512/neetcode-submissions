public static class Grid {
    private final char[][] grid;
    private final boolean[][] visited;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] ROW_DIFF = { -1, 0, 1, 0 };
    private static final int[] COL_DIFF = { 0, 1, 0, -1 };

    public Grid(char[][] grid) {
        rows = grid.length;
        assert rows > 0;

        cols = grid[0].length;
        assert cols > 0;

        this.grid = grid;

        this.visited = new boolean[rows][cols];
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void dfs(int row, int col) {
        if (!valid(row, col) || visited[row][col] || grid[row][col] == 'X') {
            return;
        }

        visited[row][col] = true;

        for (int dir = 0; dir < DIRECTIONS; dir++) {
            int nextRow = row + ROW_DIFF[dir];
            int nextCol = col + COL_DIFF[dir];
            dfs(nextRow, nextCol);
        }
    }

    public void captureSurroundedRegions() {
        for (int r = 0; r < rows; r++) {
            dfs(r, 0);
            dfs(r, cols - 1);
        }

        for (int c = 0; c < cols; c++) {
            dfs(0, c);
            dfs(rows - 1, c);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && grid[r][c] == 'O') {
                    grid[r][c] = 'X';
                }
            }
        }
    }
}

class Solution {
    public void solve(char[][] board) {
        Grid grid = new Grid(board);
        grid.captureSurroundedRegions();
    }
}
