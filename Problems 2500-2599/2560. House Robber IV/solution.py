class Solution:
    def minCapability(self, nums: List[int], k: int) -> int:
        # min-max --> binary search
        lo, hi = min(nums), max(nums)
        while lo < hi:
            mid = lo + (hi - lo) // 2
            # check if we can steal k houses
            # from nums where mid is max
            if self.canSteal(nums, k, mid):
                hi = mid
            else:
                lo = mid + 1
        return lo

    def canSteal(self, nums, k, capability):
        robbed = 0
        adj = False
        # Rob as early as possbile, give more space for room 
        # on the right side, more likely to rob total k houses
        # Let's say capability is 9 and we have: [8,9,8,10]
        # and k is 2. this will return true even though it
        # is false. but that's okay because the search will
        # eventually get to 8, and that will return true
        for num in nums:
            if adj:
                adj = False
                continue
            if num <= capability:
                robbed += 1
                adj = True
            if robbed == k:
                return True
        return False