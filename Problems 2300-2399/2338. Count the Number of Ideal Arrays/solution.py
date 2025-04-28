MOD = 1e9 + 7

class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        # dp[i][j] -> number of non-repeating ideal arrays that end in i
        # with j unique elements (len of array since non-repeating)
        # max 14 unique elements due to constraints:
        # maxValue caps off at 10^4, best case is:
        # 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192
        dp = [[0]*15 for _ in range(maxValue + 1)]
        
        # there is one way to construct array of length 1 ending in i
        for i in range(1, maxValue + 1):
            dp[i][1] = 1
        
        divisors = [[] for _ in range(maxValue + 1)]
        for div in range(1, maxValue + 1):
            for num in range(2*div, maxValue + 1, div):
                divisors[num].append(div)

        for end in range(1, maxValue + 1):
            for numUnique in range(2, min(n+1, 15)):
                for div in divisors[end]:
                    dp[end][numUnique] += dp[div][numUnique-1]
                    dp[end][numUnique] %= MOD

        # Since there's nothing in dp[0][i], since nums have to be from
        # 1 and maxValue, so let's define dp[0][i] = number of ideal arrays
        # of length i with i unique nums
        for numUnique in range(1, min(n+1, 15)):
            for end in range(1, maxValue + 1):
                dp[0][numUnique] = (dp[0][numUnique] + dp[end][numUnique]) % MOD

        memo = [[0]*15 for _ in range(n)]
        res = 0
        for numUnique in range(1, min(n+1, 15)):
            # ex. [1, 3, 6] -> how many 1's, how many 3's and how many 6's
            # if we have n slots, then the "dividers"/boundaries 
            # occur from indices 1 to n-1
            # so we have choose 2 (since we have 3 distinct values) from
            # n-1 -> nCr(n-1, 2)
            # Note: [1, 3, 6] is same as [1, 4, 8] in number of different ways
            # Fill numUnique to n slots
            res += (self.nCr(n-1, numUnique-1, memo)*dp[0][numUnique]) % MOD
            res %= MOD

        return int(res)

        # ans = maxValue
        # freq = {x : 1 for x in range(1, maxValue+1)}
        # for k in range(1, n): 
        #     temp = Counter()
        #     for x in freq: 
        #         for m in range(2, maxValue//x+1): 
        #             ans += comb(n-1, k)*freq[x]
        #             temp[m*x] += freq[x]
        #     freq = temp
        #     ans %= 1_000_000_007
        # return ans 

    def nCr(self, n, k, memo):
        if k == 0: return 1
        if n == 0: return 0

        if memo[n][k] != 0:
            return memo[n][k]

        # You have n people with different names. One of them is Tom. 
        # How many ways can you choose k of them?
        # Well, either you pick Tom and then choose k-1 from the remaining n-1. Or you don't choose Tom, and then choose all k people from the remaining n-1. This covers all possibilities with no overlap. 
        memo[n][k] = self.nCr(n-1, k, memo)+self.nCr(n-1, k-1, memo)
        memo[n][k] %= MOD
        return memo[n][k]