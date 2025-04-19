class Solution:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 0: return 0
        inc = dec = 1
        maxLen = 1
        for i in range(1, n):
            if nums[i] > nums[i-1]:
                inc += 1
                dec = 1
            elif nums[i] < nums[i-1]:
                dec += 1
                inc = 1
            else:
                inc = dec = 1
            maxLen = max(maxLen, inc, dec)
        return maxLen