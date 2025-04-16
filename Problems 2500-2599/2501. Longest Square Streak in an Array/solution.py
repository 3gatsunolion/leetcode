import math
from collections import defaultdict
class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        # dp[i] = longest streak ending on i
        n = len(nums)
        dp = defaultdict(int)
        # must sort first in order to do dp
        nums = sorted(set(nums))
        res = 0
        for num in nums:
            root = math.sqrt(num)
            # num is a perfect square
            if root*root == num:
                dp[num] = 1 + dp[root]
            else:
                dp[num] = 1
            res = max(res, dp[num])
        
        return -1 if res < 2 else res