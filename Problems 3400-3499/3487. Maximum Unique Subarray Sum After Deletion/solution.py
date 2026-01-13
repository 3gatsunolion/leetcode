class Solution:
    def maxSum(self, nums: List[int]) -> int:
        # Delete ALL negative numbers and take the sum
        unique = set([num for num in nums if num >= 0])

        # All negative numbers, so we take max negative
        if len(unique) == 0:
            return max(nums)
        
        return sum(unique)