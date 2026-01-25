MOD = 10**9 + 7
class Solution:
    def colorTheGrid(self, m: int, n: int) -> int:
        # Since m <= 5, we can represent previous column with
        # a bitmask. Each value is 1, 2, or 3. So we need
        # at most 10 bits to represent a column.
        # 2**10 = 1024
        dp = [[-1]*(2**(2*m)) for _ in range(n)]
        return self.countWays(0, 0, m, n, 0, 0, dp)
    
    def countWays(self, row, col, m, n, prev, curr, dp):
        if col == n:
            return 1
        if row == m:
            return self.countWays(0, col + 1, m, n, curr, 0, dp)
        
        if row == 0 and dp[col][prev] != -1:
            return dp[col][prev]

        left = (prev >> 2*row) & 3
        up = (curr >> 2*(row-1)) & 3 if row > 0 else 0
        res = 0
        for color in range(1, 4):
            if left == color or up == color:
                continue
            res = (res + self.countWays(row+1, col, m, n, prev, curr | (color << 2*row), dp)) % MOD

        if row == 0:
            dp[col][prev] = res
        return res
                