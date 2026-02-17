class Solution {
    // private int[] dir = new int[]{ 0, -1, 0, 1, 0 };

    // public List<List<Integer>> pacificAtlantic(int[][] heights) {
    //     int m = heights.length;
    //     int n = heights[0].length;

    //     boolean[][] pacific = new boolean[m][n];
    //     boolean[][] atlantic = new boolean[m][n];

    //     Queue<int[]> pQueue = new LinkedList<>();
    //     Queue<int[]> aQueue = new LinkedList<>();
    //     for (int i = 0; i < m; i++) {
    //         pQueue.offer(new int[]{i, 0});
    //         aQueue.offer(new int[]{i, n - 1});
    //         pacific[i][0] = true;
    //         atlantic[i][n - 1] = true;
    //     }

    //     for (int i = 0; i < n; i++) {
    //         pQueue.offer(new int[]{0, i});
    //         aQueue.offer(new int[]{m - 1, i});
    //         pacific[0][i] = true;
    //         atlantic[m - 1][i] = true;
    //     }

    //     bfs(pQueue, heights, pacific);
    //     bfs(aQueue, heights, atlantic);

    //     List<List<Integer>> res = new ArrayList<>();
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (pacific[i][j] && atlantic[i][j]) {
    //                 res.add(Arrays.asList(i, j));
    //             }
    //         }
    //     }

    //     return res;
    // }

    // private void bfs(Queue<int[]> q, int[][] heights, boolean[][] visited) {
    //     int m = heights.length;
    //     int n = heights[0].length;

    //     while (!q.isEmpty()) {
    //         int[] coord = q.poll();
    //         int x = coord[0];
    //         int y = coord[1];

    //         for (int i = 0; i < 4; i++) {
    //             int dx = x + dir[i];
    //             int dy = y + dir[i + 1];

    //             if (dx < 0 || dy < 0 || dx == m || dy == n || visited[dx][dy] || heights[dx][dy] < heights[x][y]) {
    //                 continue;
    //             }
    //             visited[dx][dy] = true;
    //             q.offer(new int[] {dx, dy});
    //         }
    //     }
    // }

    // DFS
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, heights, pacific, heights[i][0]);
            dfs(i, n - 1, heights, atlantic, heights[i][n - 1]);
        }

        for (int i = 0; i < n; i++) {
            dfs(0, i, heights, pacific, heights[0][i]);
            dfs(m - 1, i, heights, atlantic, heights[m - 1][i]);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int x, int y, int[][] heights, boolean[][] visited, int minHeight) {
        int m = heights.length;
        int n = heights[0].length;
        if (x < 0 || y < 0 || x == m || y == n || visited[x][y] || heights[x][y] < minHeight) {
            return;
        }

        visited[x][y] = true;
        dfs(x + 1, y, heights, visited, heights[x][y]);
        dfs(x - 1, y, heights, visited, heights[x][y]);
        dfs(x, y + 1, heights, visited, heights[x][y]);
        dfs(x, y - 1, heights, visited, heights[x][y]);
    }
}