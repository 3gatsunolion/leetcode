class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)
        seen = [0] * (max(nums) + 1)

        res = 0

        for l in range(n):
            # Optimization: If all remaining subarray length is less than
            # current max subarray length result, then we can break
            if n - l <= res:
                break

            parity_count = [0, 0]

            for r in range(l, n):
                val = nums[r]
                # l + 1 means val is seen in subarrays starting at index l
                # l + 1 and not l because we need to differentiate between
                # if we've seen it or not if l = 0
                if seen[val] != l + 1:
                    seen[val] = l + 1
                    parity_count[val & 1] += 1
                
                if parity_count[0] == parity_count[1]:
                    res = max(res, r - l + 1)
        
        return res