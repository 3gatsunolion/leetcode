class Solution:
    def search(self, nums: List[int], target: int) -> int:
        low, high = 0, len(nums) - 1

        while low <= high:
            # To avoid overflow:
            # mid = low + (high - low) // 2
            # mid = (low + high) >>> 1
            mid = (high + low) // 2

            if nums[mid] < target:
                low = mid + 1
            elif nums[mid] == target:
                return mid
            else:
                high = mid - 1

        return -1