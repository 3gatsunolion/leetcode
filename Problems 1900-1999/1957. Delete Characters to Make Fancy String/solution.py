class Solution:
    def makeFancyString(self, s: str) -> str:
        n = len(s)
        if n < 3:
            return s
        
        delete = [s[0]]
        l = 0
        count = 1
        for r in range(1, n):
            if s[r] == s[r-1]:
                count += 1
            else:
                count = 1

            if count < 3:
                delete.append(s[r])

        return "".join(delete)