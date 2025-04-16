class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        countSoFar = defaultdict(int)
        left = 0
        n = len(nums)
        res = 0
        for right in range(n):
            # nums[right] can form countSoFar[nums[right]]
            # pairs, since countSoFar stores how many
            # of the same number we've seen so far in 
            # current window
            k -= countSoFar[nums[right]]
            countSoFar[nums[right]] += 1

            while k <= 0:
                k += countSoFar[nums[left]] - 1
                countSoFar[nums[left]] -= 1
                left += 1
            
            res += left
        return res
            