class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        pascal = []
        for rowNum in range(1, numRows + 1):
            row = [1] * rowNum
            for i in range(1, rowNum - 1):
                row[i] = pascal[-1][i-1] + pascal[-1][i]
            pascal.append(row)
        return pascal