public static record Location(int row, int col) {}

public static class TreasureMap {
    private final int[][] map;
    private final int rows;
    private final int cols;

    private static final int DIRECTIONS = 4;
    private static final int[] ROW_DIFF = {-1, 0, 1, 0};
    private static final int[] COL_DIFF = {0, 1, 0, -1};

    public TreasureMap(int[][] map) {
        rows = map.length;
        assert rows != 0;

        cols = map[0].length;
        assert cols != 0;

        this.map = map;
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void multiSourceBFS() {
        Queue<Location> container = new ArrayDeque<>();
        int totalStarts = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (map[r][c] != 0) {
                    continue;
                }
                container.offer(new Location(r, c));
                totalStarts++;
            }
        }

        while (!container.isEmpty()) {
            Location vertex = container.poll();
            int distance = map[vertex.row()][vertex.col()];
            for (int dir = 0; dir < DIRECTIONS; dir++) {
                int nextRow = vertex.row() + ROW_DIFF[dir];
                int nextCol = vertex.col() + COL_DIFF[dir];
                if (!valid(nextRow, nextCol) || map[nextRow][nextCol] != Integer.MAX_VALUE) {
                    continue;
                }
                map[nextRow][nextCol] = Math.min(map[nextRow][nextCol], distance + 1);
                container.offer(new Location(nextRow, nextCol));
            }
        }
    }
}

class Solution {
    public void islandsAndTreasure(int[][] grid) {
        TreasureMap treasureMap = new TreasureMap(grid);
        treasureMap.multiSourceBFS();
    }
}
