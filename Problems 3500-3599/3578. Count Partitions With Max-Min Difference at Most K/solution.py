from collections import deque
class Solution:
    def countPartitions(self, nums: List[int], k: int) -> int:
        MOD = 10**9 + 7

        minq = deque()
        maxq = deque()

        n = len(nums)
        dp = [0] * (n + 1)
        dp[0] = 1

        left = 0
        accum = 1
        for right in range(n):
            while minq and nums[minq[-1]] >= nums[right]:
                minq.pop()
            minq.append(right)

            while maxq and nums[maxq[-1]] <= nums[right]:
                maxq.pop()
            maxq.append(right)

            while (nums[maxq[0]] - nums[minq[0]]) > k:
                accum = (accum - dp[left] + MOD) % MOD
                left += 1
                if minq[0] < left:
                    minq.popleft()
                
                if maxq[0] < left:
                    maxq.popleft()
            
            # We want dp[left + 1] + dp[left + 2] + ... + dp[right], so we keep running sum through accum
            dp[right + 1] = accum
            accum = (accum + dp[right + 1]) % MOD
            # accum *= 2
            # accum %= MOD

        return dp[-1]
