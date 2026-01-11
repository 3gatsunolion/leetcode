class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        j = 0
        for i, num in enumerate(nums):
            if i == 0 or nums[j-1] != num:
                nums[j] = num
                j += 1
        return j