class Solution:
    def maximumDifference(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return -1
        
        minSoFar = nums[0]
        res = -1
        for num in nums:
            if num > minSoFar:
                res = max(res, num - minSoFar)
            minSoFar = min(minSoFar, num)
        
        return res