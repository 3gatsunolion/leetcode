class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1 or numRows >= len(s):
            return s

        rows = [[] for _ in range(numRows)]

        step = 1
        currRow = 0
        for i in range(len(s)):
            rows[currRow].append(s[i])
            if currRow == 0:
                step = 1
            elif currRow == numRows - 1:
                step = -1
            currRow += step
        
        return "".join(["".join(row) for row in rows])
        