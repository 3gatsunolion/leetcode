class Solution:
    def getWordsInLongestSubsequence(self, words: List[str], groups: List[int]) -> List[str]:
        n = len(words)
        # dp[i]: max subsequence length ending at i
        dp = [1] * n
        # prev[i]: prev index for max subsequence ending at i
        prev = [-1] * n

        maxLen = 1
        maxPrev = 0
        for i in range(n):
            for j in range(i):
                if groups[i] != groups[j] and len(words[i]) == len(words[j]):
                    if self.isHammingDistOne(words[i], words[j]):
                        if dp[j] + 1 > dp[i]:
                            dp[i] = 1 + dp[j]
                            prev[i] = j

            if dp[i] > maxLen:
                maxLen = dp[i]
                maxPrev = i
        
        res = []
        while maxPrev != -1:
            res.append(words[maxPrev])
            maxPrev = prev[maxPrev]
        
        res.reverse()
        return res
        
    def isHammingDistOne(self, word1, word2):
        # Assumption: word1 and word2 are equal in length
        n = len(word1)
        
        res = 0
        for i in range(n):
            if word1[i] != word2[i]:
                res += 1
            if res > 1:
                break
        
        return res == 1