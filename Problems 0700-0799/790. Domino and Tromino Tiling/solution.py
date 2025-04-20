class Solution:
    def numTilings(self, n: int) -> int:
        # dp[n] = dp[n-1] + dp[n-2] + 2*(dp[n-3], ..., dp[0])
        # dp[n] = dp[n-1] + dp[n-2] + 2*dp[n-3] + 2(dp[n-4],.dp[0])
        # dp[n] = dp[n-1] + dp[n-3] + dp[n-2] + dp[n-3]+2(dp[n-4]..)
        # dp[n] = dp[n-1] + dp[n-3] + dp[n-1]
        # dp[n] = 2*dp[n-1] + dp[n-3]
        if n <= 2:
            return n
        mod = 10**9 + 7
        dp = [1] * (n + 1)
        # dp[0] = 1 # 1 way to tile 2x0 tiles
        # dp[1] = 1
        dp[2] = 2
        dp[3] = 5

        for i in range(4, n + 1):
            dp[i] = 2*dp[i-1] + dp[i-3]
            dp[i] %= mod
        
        return dp[n]