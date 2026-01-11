class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m = len(matrix)
        n = len(matrix[0])

        rowZero = False
        colZero = False
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 0:
                    if i == 0:
                        rowZero = True
                    if j == 0:
                        colZero = True
                    
                    if i > 0 and j > 0:
                        matrix[i][0] = 0
                        matrix[0][j] = 0
        
        for row in range(1, m):
            if matrix[row][0] == 0:
                for col in range(n):
                    matrix[row][col] = 0
        
        for col in range(n):
            if (col == 0 and colZero) or (col > 0 and matrix[0][col] == 0):
                for row in range(m):
                    matrix[row][col] = 0
        
        if rowZero:
            for col in range(n):
                matrix[0][col] = 0