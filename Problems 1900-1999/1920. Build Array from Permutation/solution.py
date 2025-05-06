class Solution:
    def buildArray(self, nums: List[int]) -> List[int]:
        # n = len(nums)
        # ans = [nums[nums[i]] for i in range(n)]
        # return ans

        # Follow-up (update inspace)
        n = len(nums)
        for i in range(n):
            # encode: n*new + old
            old = nums[i]
            # % n because it's possible this was overwritten
            # previous iteration so we % n to get old
            new = nums[nums[i]] % n
            nums[i] = n*new + old

        for i in range(n):
            # decode: old is always < n, so when we divide by
            # n, we will get new
            nums[i] //= n
        
        return nums