class Solution:
    # 1. Manacher's Algorithm
    # Time complexity: O(n)
    # Space complexity: O(n)
    def longestPalindrome(self, s: str) -> str:
        if len(s) < 2:
            return s

        # 1. Preprocessing. Interweave s with # so we don't
        # have to worry about processing odd and even palindromes
        # This will create only odd palindromes
        # -> aaa -> #a#a#a#
        # -> aaaa -> #a#a#a#a#
        # NOTE: We surround string with '#' too, because for ALL palindromes
        # it will start and end with #, and the remaining characters to the
        # left of a palindrome will be EVEN, since it will be of this form:
        # #a#b#c, so we can easily map the start index back to the original s
        t = '#' + '#'.join(s) + '#'
        
        # 2. Keep track of radius
        # radius[i] = radius of longest palindrome with center i
        # i.e. aaabaaa -> radius[3] = 'b' is 3
        n = len(t)
        radius = [0 for _ in range(n)]

        # 3. Keep track of palindrome with longest right reach so far
        # We want to do this so we don't re-traverse unnecessary parts
        # of the string again
        center = 0
        right = 0

        # Keep track of longest palindrome
        maxRadius = 0
        maxCenter = 0

        for i in range(1, n):
            if i < right:
                # mirrored index is: center - (i - center) = 2*center - i
                # Since i is part of a palindrome that has already been found
                # we know there is the same character at 2*center - i
                # And we can check radius, as if there is a palindrome extending
                # there, then the same palindrome is mirrored at i
                radius[i] = min(right - i, radius[2 * center - i])
            
            while i - radius[i] - 1 >= 0 and \
                  i + radius[i] + 1 < n and \
                  t[i - radius[i] - 1] == t[i + radius[i] + 1]:
                  radius[i] += 1
            
            if i + radius[i] > right:
                center = i
                right = i + radius[i]
            
            if radius[i] > maxRadius:
                maxRadius = radius[i]
                maxCenter = i
        
        start = (maxCenter - maxRadius) // 2
        end = start + maxRadius
        return s[start:end]

    # 2. Expand from Center
    # Time complexity: O(n^2), Space: O(1)
    # def longestPalindrome(self, s: str) -> str:
    #     longest = ""
    #     for i in range(len(s)):
    #         pali1 = self.expandFromCenter(s, i, i)
    #         pali2 = self.expandFromCenter(s, i, i + 1)
    #         if len(pali1) > len(longest):
    #             longest = pali1
    #         if len(pali2) > len(longest):
    #             longest = pali2

    #     return longest
        
    # def expandFromCenter(self, s: str, centerLeft: int, centerRight: int) -> str:
    #     while centerLeft >= 0 and centerRight < len(s) and s[centerLeft] == s[centerRight]:
    #         centerLeft -= 1
    #         centerRight += 1
        
    #     return s[centerLeft + 1:centerRight]

    # 3. DP
    # Time complexity: O(n^2), Space: O(n^2)
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