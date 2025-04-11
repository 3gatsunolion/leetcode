class Solution:
    def longestPalindrome(self, s: str) -> str:
        longest = ""
        for i in range(len(s)):
            pali1 = self.expandFromCenter(s, i, i)
            pali2 = self.expandFromCenter(s, i, i + 1)
            if len(pali1) > len(longest):
                longest = pali1
            if len(pali2) > len(longest):
                longest = pali2

        return longest
        
    def expandFromCenter(self, s: str, centerLeft: int, centerRight: int) -> str:
        while centerLeft >= 0 and centerRight < len(s) and s[centerLeft] == s[centerRight]:
            centerLeft -= 1
            centerRight += 1
        
        return s[centerLeft + 1:centerRight]

    # def longestPalindrome(self, s):
    #     n = len(s)
    #     if n <= 1:
    #         return s
    #     # dp[i][j] = is s a palindrome from s[i:j+1]
    #     dp = [[False] * n for _ in range(n)]

    #     # base cases:
    #     for i in range(n):
    #         dp[i][i] = 1
        
    #     maxLen = 1
    #     res = s[0]
    #     # starting from largest bc to know dp[i][j], we need to know
    #     # dp[i+1][j-1]
    #     for i in range(n-1, -1, -1):
    #         for j in range(i+1, n):
    #             if s[i] == s[j] and (j-i == 1 or dp[i+1][j-1]):
    #                 dp[i][j] = True
    #                 paliLen = j-i+1
    #                 if paliLen > maxLen:
    #                     maxLen = paliLen
    #                     res = s[i:j+1]
        
    #     return res

