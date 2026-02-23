class Solution:
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        n = len(nums)
        if n == 0: return 0
        # the biggest difference will be between max and min value of array
        left = 0
        minVals = deque([0])
        maxVals = deque([0])
        res = 1
        for right in range(1, n):
            num = nums[right]
            while minVals and nums[minVals[-1]] > num:
                minVals.pop()

            while maxVals and nums[maxVals[-1]] < num:
                maxVals.pop()

            minVals.append(right)
            maxVals.append(right)
            if nums[maxVals[0]] - nums[minVals[0]] > limit:
                # don't have to check minVals[0] == maxVals[0]
                # otherwise, the diff == 0 and limit >= 0
                if nums[minVals[0]] == nums[left]:
                    minVals.popleft()
                if nums[maxVals[0]] == nums[left]:
                    maxVals.popleft()
                left += 1

            res = max(res, right - left + 1)
        
        return res