public static class Grid {
    private final int[][] grid;
    private final int[][] visits;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] ROW_DIFF = {-1, 0, 1, 0};
    private static final int[] COL_DIFF = {0, 1, 0, -1};

    public Grid(int[][] grid) {
        rows = grid.length;
        assert rows > 0;

        cols = grid[0].length;
        assert cols > 0;

        this.grid = grid;

        this.visits = new int[rows][cols];
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void dfs(int row, int col, boolean[][] visited) {
        if (visited[row][col]){
            return;
        }
        
        visited[row][col] = true;

        for (int dir = 0; dir < DIRECTIONS; dir++) {
            int nextRow = row + ROW_DIFF[dir];
            int nextCol = col + COL_DIFF[dir];

            if (!valid(nextRow, nextCol) || grid[nextRow][nextCol] < grid[row][col]) {
                continue;
            }

            dfs(nextRow, nextCol, visited);
        }
    }

    private void exploreFromPacific() {
        boolean[][] visited = new boolean[rows][cols];
        for (int c = 0; c < cols; c++) {
            dfs(0, c, visited);
        }

        for (int r = 1; r < rows; r++) {
            dfs(r, 0, visited);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                visits[r][c] += visited[r][c] ? 1 : 0;
            }
        }
    }

    private void exploreFromAtlantic() {
        boolean[][] visited = new boolean[rows][cols];
        for (int c = 0; c < cols; c++) {
            dfs(rows - 1, c, visited);
        }

        for (int r = 0; r < rows - 1; r++) {
            dfs(r, cols - 1, visited);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                visits[r][c] += visited[r][c] ? 1 : 0;
            }
        }
    }

    public List<List<Integer>> findCommonCells() {
        exploreFromPacific();
        exploreFromAtlantic();

        List<List<Integer>> commonCells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (visits[r][c] != 2) {
                    continue;
                }
                commonCells.add(new ArrayList<>(List.of(r, c)));
            }
        }

        return commonCells;
    }
}

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Grid grid = new Grid(heights);
        return grid.findCommonCells();
    }
}
