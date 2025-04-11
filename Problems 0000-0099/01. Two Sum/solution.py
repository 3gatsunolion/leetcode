class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        numMap = {}
        for i, num1 in enumerate(nums):
            num2 = target - num1
            if num2 in numMap:
                return [i, numMap[num2]]
            numMap[num1] = i
