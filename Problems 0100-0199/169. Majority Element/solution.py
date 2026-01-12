class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        n = len(nums)
        cand = 0
        count = 0
        for num in nums:
            if num == cand:
                count += 1
            elif count > 0:
                count -= 1
            else:
                cand = num
                count = 1

        return cand