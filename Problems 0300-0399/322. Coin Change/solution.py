class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # if amount == 0: return 0
        # n = len(coins)
        # # dp[a][i] = minimum coins to make a with first i coins
        # dp = [[amount + 1]*(n+1) for _ in range(amount + 1)]

        # for i in range(n+1):
        #     dp[0][i] = 0

        # for a in range(1, amount + 1):
        #     for i in range(1, n+1):
        #         coin = coins[i-1]
        #         dp[a][i] = dp[a][i-1] # skip coin
        #         if coin > a:
        #             continue
        #         dp[a][i] = min(1 + dp[a-coin][i], dp[a][i])
        
        # return -1 if dp[amount][n] > amount else dp[amount][n]

        dp = [amount + 1] * (amount + 1)
        dp[0] = 0
        for amt in range(1, amount + 1):
            for coin in coins:
                if coin > amt: continue
                dp[amt] = min(dp[amt], 1 + dp[amt-coin])
        
        return -1 if dp[amount] > amount else dp[amount]