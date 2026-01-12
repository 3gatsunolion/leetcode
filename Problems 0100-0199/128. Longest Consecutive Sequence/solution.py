class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        unique = set(nums)

        res = 0
        for num in unique:
            if num-1 not in unique:
                conseq = 1
                while num+1 in unique:
                    num += 1
                    conseq += 1
                res = max(res, conseq)
        return res