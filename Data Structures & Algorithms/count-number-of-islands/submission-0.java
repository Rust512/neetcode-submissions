public static class Grid {
    private final char[][] grid;
    private final boolean[][] visited;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] rowDiff = {-1, 0, 1, 0};
    private static final int[] colDiff = {0, 1, 0, -1};

    public Grid(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int countIslands() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '0' || visited[r][c]) {
                    continue;
                }
                explore(r, c);
                count++;
            }
        }

        return count;
    }

    private void explore(int row, int col) {
        if (grid[row][col] == '0') {
            return;
        }

        visited[row][col] = true;

        for (int dir = 0; dir < DIRECTIONS; dir++) {
            int newRow = row + rowDiff[dir];
            int newCol = col + colDiff[dir];
            if (!isValid(newRow, newCol) || visited[newRow][newCol]) {
                continue;
            }
            explore(newRow, newCol);
        }
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        Grid g = new Grid(grid);
        return g.countIslands();
    }
}
