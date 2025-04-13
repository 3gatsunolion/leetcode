class Solution:
    def findMiddleIndex(self, nums: List[int]) -> int:
        totalSum = sum(num for num in nums)
        leftSum = 0

        for i, num in enumerate(nums):
            rightSum = totalSum - num - leftSum
            if leftSum == rightSum:
                return i
            leftSum += num
        
        return -1