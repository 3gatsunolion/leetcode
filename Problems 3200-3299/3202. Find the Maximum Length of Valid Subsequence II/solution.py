class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [[0]*k for _ in range(k)]

        res = 0
        for num in nums:
            curr = num % k
            for mod in range(k):
                # We want: curr + x = a*k + mod
                # x = a*k + mod - curr
                prev = (k + mod - curr) % k
                dp[curr][mod] = 1 + dp[prev][mod]
            res = max(res, max(dp[curr]))
    
        return res