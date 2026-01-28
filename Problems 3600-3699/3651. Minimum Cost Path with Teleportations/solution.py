class Solution:
    def minCost(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        # dp[i][j][k] -> min cost to get to (i, j) with at most k times
        dp = [[[float('inf')] * (k + 1) for _ in range(n)] for _ in range(m)]

        # We start at (0, 0), so min cost there is 0
        for i in range(k + 1):
            dp[0][0][i] = 0

        # First calculate min cost if we cannot teleport k = 0
        maxGridVal = grid[0][0]
        for i in range(1, m):
            dp[i][0][0] = grid[i][0] + dp[i - 1][0][0]
            maxGridVal = max(maxGridVal, grid[i][0])

        for j in range(1, n):
            dp[0][j][0] = grid[0][j] + dp[0][j - 1][0]
            maxGridVal = max(maxGridVal, grid[0][j])

        for i in range(1, m):
            for j in range(1, n):
                dp[i][j][0] = min(dp[i - 1][j][0], dp[i][j - 1][0]) + grid[i][j]
                maxGridVal = max(maxGridVal, grid[i][j])
        
        # Now calculate for k > 0
        for numTeleport in range(1, k + 1):
            # minCosts[x] -> min cost of any (i, j) with grid[i][j] >= x
            # with at most numTeleport - 1 times
            minCosts = [float('inf')] * (maxGridVal + 1)
            for i in range(m):
                for j in range(n):
                    minCosts[grid[i][j]] = min(minCosts[grid[i][j]], 
                                               dp[i][j][numTeleport - 1])
            
            for val in range(maxGridVal - 1, -1, -1):
                minCosts[val] = min(minCosts[val], minCosts[val + 1])

            for i in range(1, m):
                dp[i][0][numTeleport] = min(dp[i - 1][0][numTeleport] + grid[i][0], minCosts[grid[i][0]])
            
            for j in range(1, n):
                dp[0][j][numTeleport] = min(dp[0][j - 1][numTeleport] + grid[0][j], minCosts[grid[0][j]])
            
            for i in range(1, m):
                for j in range(1, n):
                    dp[i][j][numTeleport] = min(dp[i-1][j][numTeleport] + grid[i][j],
                                                dp[i][j - 1][numTeleport] + grid[i][j],
                                                minCosts[grid[i][j]])

        return dp[m - 1][n - 1][k]
