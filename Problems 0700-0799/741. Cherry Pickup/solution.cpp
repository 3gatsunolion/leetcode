class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> dp(n+1, vector(n+1, -1));
        // dp[i][j] -> max cherries where two paths
        // start at (0, 0) and are now at i and j with x1+y1=z
        dp[1][1] = grid[0][0];
        // x1 + y1 = z -> z -> [0, 2*n]
        int m = 2*(n-1);
        for (int total = 1; total <= m; total++) {
            for (int x1 = min(total, n-1); x1 >= 0; x1--) {
                for (int x2 = min(total, n-1); x2 >= 0; x2--) {
                    int y1 = total - x1;
                    int y2 = total - x2;
                    
                    if (y1 >= n || y2 >= n || grid[x1][y1] == -1 || grid[x2][y2] == -1) {
                    // if (y1 < 0 || y2 < 0 || y1 >= n || y2 >= n || grid[x1][y1] == -1 || grid[x2][y2] == -1) {
                        // MUST PUT THIS HERE
                        // OVERRIDE PREVIOUS VALID RESULT BECAUSE
                        // THIS ITERATION IS NO LONGER VALID
                        dp[x1+1][x2+1] = -1;
                        continue;
                    }

                    int dd = dp[x1][x2];
                    int dr = dp[x1][x2+1];
                    int rr = dp[x1+1][x2+1];
                    int rd = dp[x1+1][x2];

                    int maxPath = max(max(dd, dr), max(rr, rd));
                    if (maxPath == -1) continue;
                    dp[x1+1][x2+1] = grid[x1][y1] + maxPath;
                    if (x1 != x2) {
                        dp[x1+1][x2+1] += grid[x2][y2];
                    }

                }
            }
        }

        // 1 1 -1
        // 1 -1 1
        // -1 1 1

        return max(0, dp[n][n]);

        // // instead of one path from (0,0)->(n-1,n-1),
        // // think of it as two paths from (0,0)->(n-1,n-1)
        // // with no duplicate counting
        // // x1+y1=x2+y2 -> since each step we're adding one
        // // to the sum (can only go down and right), so they
        // // should be equal if they start
        // // at the same time at (0,0)
        // int n = grid.size();
        // vector<vector<vector<int>>> dp(n+1, vector<vector<int>>(n+1, vector<int>(n+1, -1)));
        // dp[1][1][1] = grid[0][0];
        // for (int x1 = 0; x1 < n; x1++) {
        //     for (int y1 = 0; y1 < n; y1++) {
        //         for (int x2 = 0; x2 < n; x2++) {
        //             int y2 = x1+y1-x2;
        //             if (x1 == 0 && y1 == 0) continue;
        //             if (y2 < 0 || y2 >= n || grid[x1][y1] == -1 || grid[x2][y2] == -1) continue;
        //             // x1,y1 goes down, x2,y2 goes down
        //             int dd = dp[x1][y1+1][x2];
        //             // x1,y1 goes right, x2,y2 goes right
        //             int rr = dp[x1+1][y1][x2+1];
        //             // x1,y1 goes down, x2,y2 goes right
        //             int dr = dp[x1][y1+1][x2+1];
        //             // x1,y1 goes right, x2,y2 goes down
        //             int rd = dp[x1+1][y1][x2];
        //             int maxPath = max(max(dd, rr), max(dr, rd));
        //             if (maxPath == -1) continue;
        //             dp[x1+1][y1+1][x2+1] = grid[x1][y1] + maxPath;
        //             // if not the same coord:
        //             if (x1 != x2) {
        //                 dp[x1+1][y1+1][x2+1] += grid[x2][y2];
        //             }
        //         }
        //     }
        // }

        // return dp[n][n][n] == -1 ? 0 : dp[n][n][n];

    }
    
};