class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;

        if (m < 3 || n < 3) return 0;

        int[][] waterLevel = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                waterLevel[i][j] = Integer.MAX_VALUE;

                if (i == 0 || j == 0 || i == (m - 1) || j == (n - 1)) {
                    waterLevel[i][j] = heightMap[i][j];
                }
            }
        }

        // Keep iterating until waterLevels stabalize
        while (true) {
            boolean hasChanged = false;
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    int prev = waterLevel[i][j];
                    waterLevel[i][j] = Math.max(
                        heightMap[i][j],
                        Math.min(waterLevel[i][j], Math.min(waterLevel[i - 1][j], waterLevel[i][j - 1]))
                    );
                    if (waterLevel[i][j] != prev) {
                        hasChanged = true;
                    }
                }
            }

            if (!hasChanged) break;
            hasChanged = false;

            for (int i = m - 2; i >= 1; i--) {
                for (int j = n - 2; j >= 1; j--) {
                    int prev = waterLevel[i][j];
                    waterLevel[i][j] = Math.max(
                        heightMap[i][j],
                        Math.min(waterLevel[i][j], Math.min(waterLevel[i + 1][j], waterLevel[i][j + 1]))
                    );
                    if (waterLevel[i][j] != prev) {
                        hasChanged = true;
                    }
                }
            }

            if (!hasChanged) break;
        }

        int total = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                total += waterLevel[i][j] - heightMap[i][j];
            }
        }
        return total;
    }
    // private final int[] DIR = { 0, -1, 0, 1, 0 };
    // public int trapRainWater(int[][] heightMap) {
    //     int m = heightMap.length, n = heightMap[0].length;
    //     if (m < 3 || n < 3) return 0;

    //     Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    //     // boolean[][] visited = new boolean[m][n];
    //     for (int i = 0; i < m; i++) {
    //         q.offer(new int[] {heightMap[i][0], i, 0});
    //         q.offer(new int[] {heightMap[i][n - 1], i, n - 1});
    //         // visited[i][0] = true;
    //         // visited[i][n - 1] = true;
    //         heightMap[i][0] = -1;
    //         heightMap[i][n - 1] = -1;
    //     }

    //     for (int i = 1; i < n - 1; i++) {
    //         q.offer(new int[] {heightMap[0][i], 0, i});
    //         q.offer(new int[] {heightMap[m - 1][i], m - 1, i});
    //         // visited[0][i] = true;
    //         // visited[m - 1][i] = true;

    //         heightMap[0][i] = -1;
    //         heightMap[m - 1][i] = -1;
    //     }
        
    //     int total = 0;
    //     int border = 0;
    //     while (!q.isEmpty()) {
    //         int[] curr = q.poll();
    //         int h = curr[0], x = curr[1], y = curr[2];
    //         border = Math.max(h, border);

    //         for (int i = 0; i < 4; i++) {
    //             int dx = x + DIR[i];
    //             int dy = y + DIR[i + 1];

    //             // if (dx < 0 || dy < 0 || dx == m || dy == n || visited[dx][dy]) continue;
    //             if (dx < 0 || dy < 0 || dx == m || dy == n || heightMap[dx][dy] == -1) continue;

    //             if (heightMap[dx][dy] < border) {
    //                 total += border - heightMap[dx][dy];
    //             }

    //             // visited[dx][dy] = true;
    //             q.offer(new int[] {heightMap[dx][dy], dx, dy});
    //             heightMap[dx][dy] = -1;

    //         }
    //     }
    //     return total;
    // }
}