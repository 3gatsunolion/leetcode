class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        return [0 if num == 0 else nums[(i + nums[i]) % n] for i, num in enumerate(nums)]