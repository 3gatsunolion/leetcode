DIRS = [-1, -1, 1, 1, -1]
class Solution:
    def lenOfVDiagonal(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        # dp[i][j][1][3]: longest path from i,j that has
        # already been turned (cannot turn anymore) in the
        # 3 direction
        dp = [[[[-1]*4 for _ in range(2)] for _ in range(n)] for _ in range(m)]

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    for step in range(4):
                        x = i + DIRS[step]
                        y = j + DIRS[step+1]
                        length = self.findLongestPath(x, y, grid, 2, False, step, dp)
                        res = max(res, length)
        return res
    
    def findLongestPath(self, i, j, grid, target, hasTurned, direction, dp):
        if i < 0 or j < 0 or i == len(grid) or j == len(grid[0]) or grid[i][j] != target:
            # include the 1 at the start
            return 1
        
        if dp[i][j][hasTurned][direction] != -1:
            return dp[i][j][hasTurned][direction]
        
        # 1. Don't turn, keep going in current direction
        x = i + DIRS[direction]
        y = j + DIRS[direction+1]
        sameDirection = 1 + self.findLongestPath(x, y, grid, 2 - target, hasTurned, direction, dp)

        # 2. Turn if haven't turned yet
        turn = 0
        if not hasTurned:
            x = i + DIRS[(direction+1) % 4]
            y = j + DIRS[(direction+2) % 4]
            turn = 1 + self.findLongestPath(x, y, grid, 2 - target, True, (direction+1)%4, dp)

        dp[i][j][hasTurned][direction] = max(sameDirection, turn)
        return dp[i][j][hasTurned][direction]