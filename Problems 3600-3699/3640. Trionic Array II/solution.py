class Solution:
    # def maxSumTrionic(self, nums: List[int]) -> int:
    #     #                 dp[1]       /dp[3]
    #     #                /  \        /
    #     #               /    \      /
    #     # ---dp[0]---- /     dp[2]
        

    #     # dp[0] = start phase
    #     # dp[1] = 1st increasing phase
    #     # dp[2] = 1st decreasing phase
    #     # dp[3] = 2nd increasing

    #     n = len(nums)
    #     dp = [[float("-inf")] * 4 for _ in range(n)]
    #     dp[0][0] = nums[0]
    #     res = float("-inf")
    #     for i in range(1, n):
    #         dp[i][0] = nums[i]
            
    #         if nums[i] > nums[i - 1]:
    #             # Start increasing here OR we are already in increasing phase
    #             dp[i][1] = nums[i] + max(dp[i - 1][0], dp[i - 1][1])
            
    #         if nums[i] < nums[i - 1]:
    #             # Start decreasing here OR we are already in decreasing phase
    #             dp[i][2] = nums[i] + max(dp[i - 1][1], dp[i - 1][2])

    #         if nums[i] > nums[i - 1]:
    #             dp[i][3] = nums[i] + max(dp[i - 1][2], dp[i - 1][3])
            
    #         res = max(res, dp[i][3])
    #     return res

    # def maxSumTrionic(self, nums: List[int]) -> int:
    #     n = len(nums)
    #     dp1 = float("-inf") # 1st increasing phase
    #     dp2 = float("-inf") # 1st decreasing phase
    #     dp3 = float("-inf") # 2nd increasing phase
    #     res = float("-inf")
    #     for i in range(1, n):
    #         prev = nums[i - 1]
    #         curr = nums[i]

    #         inc1 = float("-inf")
    #         dec1 = float("-inf")
    #         inc2 = float("-inf")
            
    #         if curr > prev:
    #             inc1 = curr + max(prev, dp1)
            
    #         if curr < prev:
    #             dec1 = curr + max(dp1, dp2)

    #         if nums[i] > nums[i - 1]:
    #             inc2 = curr + max(dp2, dp3)
            
    #         dp1 = inc1
    #         dp2 = dec1
    #         dp3 = inc2
    #         res = max(res, dp3)
    #     return res

    def maxSumTrionic(self, nums: List[int]) -> int:
        n = len(nums)
        # 0 -> initial state
        # 1 -> first increasing state
        # 2 -> decreasing state
        # 3 -> second increasing state
        state = 0
        # initial: first num in current trionic subarray
        # sum1: sum of current subarray from state 1 to 2
        # sum2: sum of current subarray in state 3
        initial, sum1, sum2 = 0, 0, 0
        res = float("-inf")

        for i in range(1, n):
            prev, curr = nums[i - 1], nums[i]

            if prev < curr:
                if state == 0:
                    state = 1
                    initial = prev
                    sum1 = prev + curr
                elif state == 1:
                    # since we want max sum, and it we were already increasing
                    # we can cut off the initial negative number to maximize
                    # sum
                    if initial < 0:
                        sum1 -= initial
                        initial = prev
                    sum1 += curr
                elif state == 2:
                    state = 3
                    sum2 = sum1 + curr
                    # reset sum1, since this is an increasing phase
                    # which we could use it as our state 1 if we start
                    # decreasing after
                    initial = prev
                    sum1 = prev + curr

                    if sum2 > res:
                        res = sum2
                else:
                    if initial < 0:
                        sum1 -= initial
                        initial = prev
                    sum1 += curr

                    sum2 += curr
                    if sum2 > res:
                        res = sum2
                    
            elif prev > curr:
                # if state = 0 -> nothing changes (we need either state 1 or 3
                # before a decreasing state)
                if state > 0:
                    state = 2
                    sum1 += curr
            else:
                state = 0
        return res