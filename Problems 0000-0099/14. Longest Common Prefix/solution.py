class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        # if len(strs) == 0:
        #     return ""

        # shortest = min(strs, key=len)
        # for i, ch in enumerate(shortest):
        #     for s in strs:
        #         if s[i] != shortest[i]:
        #             return shortest[:i]
        # return shortest

        if len(strs) == 0:
            return ""

        strs.sort()
        n = min(len(strs[0]), len(strs[-1]))
        for i in range(n):
            if strs[0][i] != strs[-1][i]:
                return strs[0][:i]
        return strs[0]
            
        