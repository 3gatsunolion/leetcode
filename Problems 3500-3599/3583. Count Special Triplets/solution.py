from collections import defaultdict

class Solution:
    def specialTriplets(self, nums: List[int]) -> int:
        # MOD = 10**9 + 7

        # leftCount = defaultdict(int)
        # rightCount = defaultdict(int)

        # for num in nums:
        #     rightCount[num] += 1

        # res = 0
        # for mid in nums:
        #     rightCount[mid] -= 1
            
        #     target = mid * 2
        #     l = leftCount[target]
        #     r = rightCount[target]

        #     res = (res + l * r) % MOD

        #     leftCount[mid] += 1

        # return res

        MOD = 10**9 + 7

        count = defaultdict(int)
        # We need: a, b, a -> keep track of counts of ab, store with key a
        pairs = defaultdict(int)

        res = 0
        for num in nums:
            if num in pairs:
                res = (res + pairs[num]) % MOD

            if num * 2 in count:
                pairs[num * 2] = (pairs[num * 2] + count[num * 2]) % MOD

            count[num] += 1
        
        return res
