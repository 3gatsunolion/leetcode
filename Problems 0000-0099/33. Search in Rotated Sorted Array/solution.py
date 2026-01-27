class Solution:
    # def search(self, nums: List[int], target: int) -> int:
    #     lo = 0
    #     hi = len(nums) - 1
        
    #     while lo < hi:
    #         mid = (lo + hi) // 2

    #         # Pivot GUARANTEED on right side
    #         if nums[mid] > nums[hi]:
    #             # target > nums[mid] -> Since pivot is on right side,
    #             # left side is sorted, so if target > nums[mid], then
    #             # if it exists, it HAS to be on the right side
    #             # target <= nums[hi] -> Since pivot is on right side
    #             # all nums on the left side is > nums[hi], so in this
    #             # case, we have to move to the right
    #             # -> Note: we swap target <= nums[hi] with target < nums[lo]
    #             if target > nums[mid] or target < nums[lo]:
    #                 lo = mid + 1
    #             else:
    #                 hi = mid
    #         else:
    #             # If there is a pivot, it's on left side, so right side
    #             # is completely sorted, so if target is between
    #             # nums[mid + 1] and nums[hi], then go to the right side
    #             if target <= nums[hi] and target > nums[mid]:
    #                 lo = mid + 1
    #             else:
    #                 hi = mid
        
    #     return lo if nums[lo] == target else -1

    def search(self, nums: List[int], target: int) -> int:
        # 1. First find the pivot
        n = len(nums)
        lo = 0
        hi = n - 1

        while lo < hi:
            mid = (lo + hi) // 2

            if nums[mid] > nums[hi]:
                lo = mid + 1
            else:
                hi = mid
        
        # 2. Regular binary search adjust using pivot we know
        # to adjust
        rot = lo
        lo = 0
        hi = n - 1

        while lo < hi:
            mid = (lo + hi) // 2
            realMid = (mid + rot) % n

            if nums[realMid] < target:
                lo = mid + 1
            else:
                hi = mid
        
        lo = (lo + rot) % n

        return lo if nums[lo] == target else -1
