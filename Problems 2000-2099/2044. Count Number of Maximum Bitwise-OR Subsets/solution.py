class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        # Max bitwise OR is just all nums, since it's OR

        prevBits = Counter([0])
        for num in nums:
            for prev, count in list(prevBits.items()):
                prevBits[prev | num] += count
        return prevBits[reduce(lambda a,b: a | b, nums)]

        # dp = [0] * (1 << 17)
        # dp[0] = 1
        # maxOr = 0

        # for num in nums:
        #     for i in range(maxOr, -1, -1):
        #         dp[i | num] += dp[i]
            
        #     maxOr |= num
        
        # return dp[maxOr]


        # maxOr = 0
        # for num in nums:
        #     maxOr |= num

        # def countSubsets(i, nums, curr, target):
        #     if i == len(nums):
        #         if curr == target:
        #             return 1
        #         return 0
            
        #     return countSubsets(i + 1, nums, curr | nums[i], target) + \
        #     countSubsets(i + 1, nums, curr, target)
        
        # return countSubsets(0, nums, 0, maxOr)