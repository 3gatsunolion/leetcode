class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLen = len(s)
        pLen = len(p)
        dp = [[False] * (pLen + 1) for _ in range(sLen + 1)]

        # base cases: if s is "", it matches if p "" or "*"
        # or "_*_*_*_*" pattern
        dp[0][0] = True
        for j in range(1, pLen + 1):
            # for cases like a* or a*b*
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2] if j >= 2 else dp[0][j]

        # Already covered when s is 0 in length
        # Other than when s and p are "", if sLen > 1
        # and p is empty, then that is always False
        for i in range(1, sLen + 1):
            for j in range(1, pLen + 1):
                if s[i-1] == p[j-1] or p[j-1] == ".":
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == "*":
                    if j < 2:
                        continue
                    # s = "xa", p = "xa*" == s = "x", p = "xa*"
                    # s = "xb", p = "x.*"
                    dp[i][j] = dp[i][j-2] # p="x.*" == p="x"
                    if s[i-1] == p[j-2] or p[j-2] == ".":
                        # "_*" absorbs s[i-1] if p[j-2]
                        dp[i][j] |= dp[i-1][j]
        
        return dp[sLen][pLen]