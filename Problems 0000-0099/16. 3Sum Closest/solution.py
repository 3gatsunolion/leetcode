class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        n = len(nums)
        nums.sort()
        closest = float('inf')
        for i in range(0, n - 2):
            # skip repeat
            if i > 0 and nums[i] == nums[i-1]:
                continue
            
            # optimization #1:
            maxSum = nums[i] + nums[-2] + nums[-1]
            if maxSum < target:
                # then no need to go any further
                if abs(target - maxSum) < abs(target - closest):
                    closest = maxSum
                continue
            
            # optimization #2:
            minSum = nums[i] + nums[i+1] + nums[i+2]
            if minSum > target:
                if abs(target - minSum) < abs(target - closest):
                    closest = minSum
                break
            
            l, r = i + 1, n - 1
            while l < r:
                sumVal = nums[i] + nums[l] + nums[r]
                if abs(target - sumVal) < abs(target - closest):
                    closest = sumVal
                if sumVal == target:
                    return target
                elif sumVal < target:
                    l += 1
                else:
                    r -= 1
        return closest
        