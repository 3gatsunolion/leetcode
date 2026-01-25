class Solution:
    def canJump(self, nums: List[int]) -> bool:
        # farthestReachSoFar = 0
        # for i in range(len(nums)):
        #     if farthestReachSoFar < i:
        #         return False

        #     reachFromHere = i + nums[i]
        #     if reachFromHere > farthestReachSoFar:
        #         farthestReachSoFar = reachFromHere
        
        # return True

        n = len(nums)
        goal = n - 1
        for i in range(n - 1, -1, -1):
            if i + nums[i] >= goal:
                goal = i
        
        return goal == 0