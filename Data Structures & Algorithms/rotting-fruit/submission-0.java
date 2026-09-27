public static record Instance(int row, int col, int time) {}

public static class Grid {
    private final int[][] grid;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] ROW_DIFF = {-1, 0, 1, 0};
    private static final int[] COL_DIFF = {0, 1, 0, -1};

    public Grid(int[][] map) {
        rows = map.length;
        assert rows != 0;

        cols = map[0].length;
        assert cols != 0;

        this.grid = map;
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int timeToRot() {
        Queue<Instance> container = new ArrayDeque<>();
        int fruitCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    container.offer(new Instance(r, c, 0));
                    continue;
                }
                if (grid[r][c] == 1) {
                    fruitCount++;
                }
            }
        }

        int time = 0;

        while (!container.isEmpty()) {
            Instance vertex = container.poll();
            int currentTime = vertex.time();
            for (int dir = 0; dir < DIRECTIONS; dir++) {
                int nextRow = vertex.row() + ROW_DIFF[dir];
                int nextCol = vertex.col() + COL_DIFF[dir];
                if (!valid(nextRow, nextCol) || grid[nextRow][nextCol] != 1) {
                    continue;
                }
                container.offer(new Instance(nextRow, nextCol, currentTime + 1));
                time = Math.max(time, currentTime + 1);
                grid[nextRow][nextCol] = 2;
                fruitCount--;
            }
        }

        return fruitCount != 0 ? -1 : time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        Grid g = new Grid(grid);
        return g.timeToRot();
    }
}
