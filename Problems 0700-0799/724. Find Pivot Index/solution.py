class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        n = len(nums)
        prefixSums = [0]*(n+1)
        for i in range(n):
            prefixSums[i+1] = prefixSums[i] + nums[i]
        
        for i in range(n):
            left = prefixSums[i]
            right = prefixSums[n] - prefixSums[i+1]
            if left == right:
                return i
        return -1