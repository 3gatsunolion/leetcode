class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        # nums.sort()
        # l = 0
        # max_len = 0
        # for r in range(len(nums)):
        #     while nums[r] > nums[l] * k:
        #         l += 1
        #     max_len = max(max_len, r - l + 1)
        
        # return len(nums) - max_len

        nums.sort()
        l = 0
        
        for num in nums:
            if num > nums[l] * k:
                l += 1
        
        return l