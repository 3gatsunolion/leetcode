class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 3: return n
        j = 2
        for i in range(2, n):
            if nums[i] != nums[j-2]:
                nums[j] = nums[i]
                j += 1
        return j