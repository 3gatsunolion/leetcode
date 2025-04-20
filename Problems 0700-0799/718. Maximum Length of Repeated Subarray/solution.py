class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        dp = [0]*(n+1)
        res = 0
        for i in range(m):
            for j in range(n-1, -1, -1):
                if nums1[i] == nums2[j]:
                    dp[j+1] = 1 + dp[j]
                else:
                    dp[j+1] = 0
            res = max(res, max(dp))
        return res
        # m, n = len(nums1), len(nums2)
        # dp = [[0]*(n+1) for _ in range(m+1)]
        # res = 0
        # for i in range(m):
        #     for j in range(n):
        #         if nums1[i] == nums2[j]:
        #             dp[i+1][j+1] = 1 + dp[i][j]
        #         res = max(res, dp[i+1][j+1])
        # return res