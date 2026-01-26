class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        left = 0
        currSum = 0
        res = float('inf')
        for right in range(0, len(nums)):
            currSum += nums[right]

            while currSum >= target:
                res = min(res, right - left + 1)
                currSum -= nums[left]
                left += 1

            # optimization: 1 is smallest answer, so no need
            # to go further
            if res == 1:
                return res
        return res if res != float('inf') else 0