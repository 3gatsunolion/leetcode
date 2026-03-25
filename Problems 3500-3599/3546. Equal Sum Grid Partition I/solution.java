class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long[] rowCount = new long[m];
        long[] colCount = new long[n];

        long totalSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowCount[i] += grid[i][j];
                colCount[j] += grid[i][j];
            }
            totalSum += rowCount[i];
        }

        if (totalSum % 2 == 1) return false;

        long target = totalSum / 2;
        long rowPrefix = rowCount[0];
        for (int i = 1; i < m; i++) {
            if (rowPrefix == target) return true;
            if (rowPrefix > target) break;
            rowPrefix += rowCount[i];
        }

        long colPrefix = colCount[0];
        for (int j = 1; j < n; j++) {
            if (colPrefix == target) return true;
            if (colPrefix > target) return false;
            colPrefix += colCount[j];
        }

        return false;
    }
}