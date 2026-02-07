class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        left = 0
        seen = set()
        curr = 0
        res = 0
        for right in range(len(nums)):
            curr += nums[right]
            while nums[right] in seen:
                curr -= nums[left]
                seen.remove(nums[left])
                left += 1
            
            seen.add(nums[right])
            if curr > res:
                res = curr
        
        return res

        # lastSeen = {}
        # left = 0
        # res = 0
        # prefixSum = [0]
        # for right in range(len(nums)):
        #     prefixSum.append(prefixSum[-1] + nums[right])
        #     if nums[right] in lastSeen:
        #         left = max(left, lastSeen[nums[right]] + 1)
        #     lastSeen[nums[right]] = right
        #     res = max(res, prefixSum[right + 1] - prefixSum[left])
        # return res
            