class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        n = len(nums)
        if n == 0:
            return [-1, -1]
        first = bisect_left(nums, target)
        if first == n or nums[first] != target:
            return [-1, -1]
        last = bisect_right(nums, target) - 1

        return [first, last]