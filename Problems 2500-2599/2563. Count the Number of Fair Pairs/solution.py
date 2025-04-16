class Solution:
    def countFairPairs(self, nums: List[int], lower: int, upper: int) -> int:
        # nums.sort()
        # total = 0
        # for i, num in enumerate(nums):
        #     lo = bisect.bisect_left(nums, lower-num, i+1)
        #     hi = bisect.bisect_right(nums, upper-num, i+1)
        #     # lo = self.binarySearchLeft(nums, lower - num, i+1,n)
        #     # hi = self.binarySearchRight(nums, upper-num, i+1,n)
        #     total += hi - lo
        # return total

        nums.sort()
        return self.countLess(nums, upper+1) - self.countLess(nums, lower)

    def countLess(self, nums, target):
        lo, hi = 0, len(nums) - 1
        ans = 0
        while lo < hi:
            total = nums[lo] + nums[hi]
            if total < target:
                # hi - lo pairs with nums[lo]
                ans += hi - lo
                lo += 1
            else:
                hi -= 1
        return ans

    # def binarySearchLeft(self, nums, target, lo, hi):
    #     while lo < hi:
    #         mid = lo + (hi - lo) // 2
    #         if nums[mid] < target:
    #             lo = mid + 1
    #         else:
    #             hi = mid
    #     return lo

    # def binarySearchRight(self, nums, target, lo, hi):
    #     while lo < hi:
    #         mid = lo + (hi - lo) // 2
    #         if nums[mid] <= target:
    #             lo = mid + 1
    #         else:
    #             hi = mid
    #     return lo