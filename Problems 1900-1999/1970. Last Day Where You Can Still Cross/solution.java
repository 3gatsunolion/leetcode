class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            int tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }

        parent[rootY] = rootX;

        if (rank[rootX] == rank[rootY]) {
            rank[rootX]++;
        }
    }
};

class Solution {
    int[] DIRS = {-1, 0, -1, 1, 0, 1, 1, -1, -1};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int total = row * col + 2;
        UnionFind uf = new UnionFind(total);
        
        // If we can't walk from top row to bottom row
        // that means there's a horizontal line somewhere on the grid
        // of water, so we'll keep a leftWater and rightWater, and as
        // soon as these are the same set, then we can no longer walk
        int leftWater = row * col;
        int rightWater = row * col + 1;

        boolean[][] isWater = new boolean[row][col];
        for (int day = 0; day < cells.length; day++) {
            int x = cells[day][0] - 1;
            int y = cells[day][1] - 1;
            int pos = x * col + y;

            isWater[x][y] = true;

            if (y == 0) uf.union(pos, leftWater);
            if (y == col - 1) uf.union(pos, rightWater);

            // NOTE: water can "connect" diagonally (can't walk
            // past two diagonal water cells)
            for (int i = 0; i < 8; i++) {
                int dx = x + DIRS[i];
                int dy = y + DIRS[i + 1];

                if (dx < 0 || dy < 0 || dx == row || dy == col || !isWater[dx][dy]) continue;
                uf.union(pos, dx * col + dy);
            }

            if (uf.isConnected(leftWater, rightWater)) {
                return day;
            }
        }

        return cells.length;
    }

    // public int latestDayToCross(int row, int col, int[][] cells) {
    //     int lo = 1;
    //     int hi = cells.length;

    //     while (lo < hi) {
    //         int mid = lo + (hi - lo + 1) / 2;

    //         if (canWalk(row, col, cells, mid)) {
    //             lo = mid;
    //         } else {
    //             hi = mid - 1;
    //         }
    //     }

    //     return lo;
    // }

    // int[] DIRS = new int[] {0, 1, 0, -1, 0};

    // private boolean canWalk(int row, int col, int[][] cells, int day) {
    //     int[][] grid = new int[row][col];

    //     for (int i = 0; i < day; i++) {
    //         grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
    //     }

    //     Queue<Integer> q = new LinkedList<>();

    //     for (int c = 0; c < col; c++) {
    //         if (grid[0][c] == 0) {
    //             q.add(c);
    //         }
    //     }

    //     while (!q.isEmpty()) {
    //         int pos = q.poll();

    //         int x = pos / col;
    //         int y = pos % col;

    //         // Made it to the bottom row!
    //         if (x == row - 1) return true;

    //         for (int i = 0; i < 4; i++) {
    //             int dx = x + DIRS[i];
    //             int dy = y + DIRS[i + 1];

    //             if (dx < 0 || dy < 0 || dx == row || dy == col || grid[dx][dy] == 1) continue;
    //             grid[dx][dy] = 1;
    //             q.add(dx * col + dy);
    //         }
    //     }

    //     return false;
    // }

    private boolean canWalk(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col];

        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        for (int c = 0; c < col; c++) {
            if (grid[0][c] == 0 && dfs(0, c, grid)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || c < 0 || r == m || c == n || grid[r][c] == 1) return false;
        if (r == m - 1) return true;

        grid[r][c] = 1; // Mark as visited

        for (int i = 0; i < 4; i++) {
            int rx = r + DIRS[i];
            int ry = c + DIRS[i + 1];

            if (dfs(rx, ry, grid)) return true;
        }

        return false;
    }
}