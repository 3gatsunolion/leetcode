class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if n < 2 or k < 2:
            return 0

        nums.sort()
        res = float('inf')
        for i in range(k - 1, n):
            diff = nums[i] - nums[i - k + 1]
            res = min(res, diff)
        return res