class Solution:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        res = []
        numConsec = 0
        for i, num in enumerate(nums):
            if i == 0 or nums[i-1] == nums[i] - 1:
                numConsec += 1
            else:
                numConsec = 1
            
            if i >= k-1:
                if numConsec >= k:
                    res.append(nums[i])
                else:
                    res.append(-1)
        return res

            