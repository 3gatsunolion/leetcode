class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        # dp[i] = cost[i] + min(dp[i+1], dp[i+2])
        # cost to get from i to top
        # dp = [float("inf")] * (n+1)
        # dp[n] = 0
        # dp[n-1] = cost[n-1]
        jumpOne = cost[n-1]
        jumpTwo = 0

        for i in range(n-2, -1, -1):
            # dp[i] = cost[i] + min(dp[i+1], dp[i+2])
            dp = cost[i] + min(jumpOne, jumpTwo)
            jumpTwo = jumpOne
            jumpOne = dp

        return min(jumpOne, jumpTwo)
