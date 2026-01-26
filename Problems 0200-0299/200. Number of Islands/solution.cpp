class Solution {
    public:
        int numIslands(vector<vector<char>>& grid) {
            int m = grid.size();
            int n = grid[0].size();
    
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        res++;
                        dfs(i, j, grid);
                    }
                }
            }
    
            return res;
        }
    
        void dfs(int i, int j, vector<vector<char>>& grid) {
            int m = grid.size();
            int n = grid[0].size();
    
            if (i < 0 || j < 0 || i == m || j == n || grid[i][j] != '1') return;
    
            grid[i][j] = '#';
            dfs(i+1, j, grid);
            dfs(i, j+1, grid);
            dfs(i-1, j, grid);
            dfs(i, j-1, grid);
        }
    };