class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = findRow(matrix, target);

        int startCol = 0;
        int endCol = matrix[0].length - 1;

        while (startCol <= endCol) {
            int midCol = startCol + (endCol - startCol) / 2;
            if (matrix[row][midCol] == target) {
                return true;
            }

            if (matrix[row][midCol] < target) {
                startCol = midCol + 1;
            } else {
                endCol = midCol - 1;
            }
        }

        return false;
    }

    private int findRow(int[][] matrix, int target) {
        int startRow = 0;
        int endRow = matrix.length - 1;

        int row = 0;
        while (startRow <= endRow) {
            int midRow = startRow + (endRow - startRow) / 2;
            if (matrix[midRow][0] == target) {
                return midRow;
            }

            if (matrix[midRow][0] < target) {
                startRow = midRow + 1;
                row = midRow;
            } else {
                endRow = midRow - 1;
            }
        }

        return row;
    }
}
