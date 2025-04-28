class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        n = len(nums)
        left = 0
        currSum = 0
        res = 0
        for right in range(n):
            currSum += nums[right]
            while currSum*(right-left+1) >= k:
                currSum -= nums[left]
                left += 1
            # Count number of different subarrays that end at right
            # since nums[i] is > 0, as we grow a subarray, then result
            # will always grow, so conversely, when we decrease subarray
            # size, the score will decrease as well
            res += right - left + 1
        return res