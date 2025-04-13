class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        res = []
        self.kSum(nums, 4, target, res)
        return res

    def kSum(self, nums, k, target, res):
        nums.sort()
        self.kSumHelper(nums, 0, len(nums) - 1, k, [], target, res)

    def kSumHelper(self, nums, start, end, k, currNums, target, res):
        if k == 2:
            if nums[start] + nums[start + 1] > target:
                return
            if nums[end] + nums[end-1] < target:
                return

            l, r = start, end
            while l < r:
                sumTotal = nums[l] + nums[r]
                if sumTotal == target:
                    res.append(currNums[:] + [nums[l], nums[r]])
                    while l < r and nums[l] == nums[l + 1]:
                        l += 1
                    while l < r and nums[r] == nums[r - 1]:
                        r -= 1
                    l += 1
                    r -= 1
                elif sumTotal < target:
                    l += 1
                else:
                    r -= 1
        else:
            for i in range(start, end - k + 2):
                if i > start and nums[i-1] == nums[i]:
                    continue
                
                # optimization
                if target < nums[i]*k or target > nums[end]*k:
                    break
                currNums.append(nums[i])
                self.kSumHelper(nums, i + 1, end, k - 1, currNums, target - nums[i], res)
                currNums.pop()
        