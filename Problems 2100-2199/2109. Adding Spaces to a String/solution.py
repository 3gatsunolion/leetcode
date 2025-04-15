class Solution:
    def addSpaces(self, s: str, spaces: List[int]) -> str:
        m, n = len(s), len(spaces)
        res = [' '] * (m + n)
        j = 0
        for i in range(m):
            if j < n and spaces[j] == i:
                j += 1
            res[i+j] = s[i]
        
        return "".join(res)