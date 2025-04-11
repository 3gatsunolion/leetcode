class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        charMap = {}
        maxLen = 0
        start = 0
        for end in range(len(s)):
            if s[end] not in charMap or charMap[s[end]] < start:
                maxLen = max(maxLen, end - start + 1)
            else:
                start = charMap[s[end]] + 1
            charMap[s[end]] = end
        return maxLen

    # def lengthOfLongestSubstring(self, s: str) -> int:
    #     seen = set()
    #     maxLen = 0
    #     start = 0
    #     for end in range(len(s)):
    #         if s[end] not in seen:
    #             maxLen = max(maxLen, end - start + 1)
    #         else:
    #             while s[end] in seen:
    #                 seen.remove(s[start])
    #                 start += 1
    #         seen.add(s[end])
    #     return maxLen