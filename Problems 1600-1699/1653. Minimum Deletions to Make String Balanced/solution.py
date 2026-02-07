class Solution:
    def minimumDeletions(self, s: str) -> int:
        # # Need: all 'a's then all 'b's
        # 1. DP
        # n = len(s)
        # # dp[i][0] = min deletions to make s[:i] balanced by making whole
        # # string 'a'
        # # dp[i][1] = min deletions to make s[:i] balanced where we're ending
        # # with a 'b'
        # dp = [[0] * 2 for _ in range(n + 1)]

        # for i in range(n):
        #     if s[i] == 'a':
        #         # Keep this, then all preceding characters must be a
        #         dp[i + 1][0] = dp[i][0]
        #         # Delete this
        #         dp[i + 1][1] = 1 + dp[i][1]
        #     else:
        #         dp[i + 1][0] = 1 + dp[i][0]
        #         dp[i + 1][1] = min(dp[i][1], dp[i][0])
        
        # return min(dp[n][0], dp[n][1])

        # 2. DP O(1) Space
        # n = len(s)
        # a = 0
        # b = 0

        # for i in range(n):
        #     if s[i] == 'a':
        #         b += 1
        #     else:
        #         new_a = a + 1
        #         b = min(b, a)
        #         a = new_a
        
        # return min(a, b)

        # # 3. b count
        # n = len(s)
        # res = 0
        # bcount = 0

        # for i in range(n):
        #     if s[i] == 'a':
        #         # 1. keep s[i]: delete all b's
        #         # 2. delete s[i]
        #         res = min(1 + res, bcount)
        #     else:
        #         # don't delete because we can always append b to a valid string
        #         bcount += 1
        
        # return res

        # 4. "Stack"/greedy approach
        num_b_on_top = 0
        num_deletions = 0
        for c in s:
            if c == 'b':
                num_b_on_top += 1
            elif num_b_on_top:
                # c is 'a', so if there's b's on top of stack
                # we need to:
                # 1. delete a -> increment res by 1
                # we also decrement num_b_on_top in case of what may
                # happen in 2. (won't affect solution if we want to delete a)
                # 2. if the best solution involves deleting all the top b's
                # here, that MUST mean that there are more a's coming after
                # than the b's on top, in which case all the a's will cancel
                # out all the b's, and we still have the correct number
                # of deletions
                num_deletions += 1
                num_b_on_top -= 1
        
        return num_deletions