class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()
        n = len(nums)
        for i in range(0, n - 2):
            if i > 0 and nums[i-1] == nums[i]:
                continue
            if nums[i] > 0: break
            l, r = i + 1, n - 1
            while l < r:
                sumVal = nums[i] + nums[l] + nums[r]
                if sumVal < 0:
                    l += 1
                elif sumVal > 0:
                    r -= 1
                else:
                    res.append([nums[i], nums[l], nums[r]])
                    while l < r and nums[l] == nums[l+1]:
                        l += 1
                    while l < r and nums[r] == nums[r-1]:
                        r -= 1
                    l += 1
                    r -= 1
        
        return res
