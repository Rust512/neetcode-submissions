public static class Grid {
    private final int[][] grid;
    private final boolean[][] visited;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] rowDiff = {-1, 0, 1, 0};
    private static final int[] colDiff = {0, 1, 0, -1};

    public Grid(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int maxIslandArea() {
        int maxArea = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0 || visited[r][c]) {
                    continue;
                }
                maxArea = Math.max(maxArea, countCells(r, c));
            }
        }

        return maxArea;
    }

    private int countCells(int row, int col) {
        if (grid[row][col] == 0) {
            return 0;
        }

        visited[row][col] = true;

        int cells = 1;
        for (int dir = 0; dir < DIRECTIONS; dir++) {
            int newRow = row + rowDiff[dir];
            int newCol = col + colDiff[dir];
            if (!isValid(newRow, newCol) || visited[newRow][newCol]) {
                continue;
            }
            cells += countCells(newRow, newCol);
        }

        return cells;
    }
}

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        Grid g = new Grid(grid);
        return g.maxIslandArea();
    }
}
