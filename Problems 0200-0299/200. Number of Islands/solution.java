class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        rank = new int[n];
        count = n;
    }

    public int getCount() {
        return count;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            union(y, x);
            return;
        }

        count--;
        parent[rootY] = rootX;
        if (rank[rootX] == rank[rootY]) {
            rank[rootX]++;
        }
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        // int m = grid.length;
        // int n = grid[0].length;
        // UnionFind uf = new UnionFind(m * n);
        // int water = 0;
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[i].length; j++) {
        //         if (grid[i][j] == '1') {
        //             if (i < m - 1 && grid[i + 1][j] == '1') {
        //                 uf.union(i * n + j, (i + 1) * n + j);
        //             }
        //             if (j < n - 1 && grid[i][j + 1] == '1') {
        //                 uf.union(i * n + j, i * n + j + 1);
        //             }
        //         } else {
        //             water++;
        //         }
        //     }
        // }
        // return uf.getCount() - water;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(i, j, grid);
                    // bfs(i, j, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, char[][] grid) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';
        dfs(x + 1, y, grid);
        dfs(x - 1, y, grid);
        dfs(x, y + 1, grid);
        dfs(x, y - 1, grid);
    }

    private int[] dir = { 0, -1, 0, 1, 0 };

    private void bfs(int row, int col, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        grid[row][col] = '0';
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {row, col});

        while (!q.isEmpty()) {
            int[] coord = q.poll();
            int x = coord[0], y = coord[1];
            
            for (int i = 0; i < 4; i++) {
                int dx = x + dir[i], dy = y + dir[i + 1];
                if (dx < 0 || dy < 0 || dx == m || dy == n || grid[dx][dy] == '0') {
                    continue;
                }
                grid[dx][dy] = '0';
                q.offer(new int[] {dx, dy});
            }
        }
    }
}