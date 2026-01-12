class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m = len(board)
        n = len(board[0])
        # Mark border "O" blobs as something else, like "#"
        def markEdgeRegions(i, j):
            if i < 0 or j < 0 or i == m or j == n or board[i][j] != 'O':
                return

            board[i][j] = '#'
            markEdgeRegions(i+1, j)
            markEdgeRegions(i-1, j)
            markEdgeRegions(i, j+1)
            markEdgeRegions(i, j-1)

        for i in range(m):
            if board[i][0] == 'O':
                markEdgeRegions(i, 0)
            if board[i][n-1] == 'O':
                markEdgeRegions(i, n-1)
        
        for j in range(n):
            if board[0][j] == 'O':
                markEdgeRegions(0, j)
            if board[m-1][j] == 'O':
                markEdgeRegions(m-1, j)
        
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    board[i][j] = 'X'
                elif board[i][j] == '#':
                    board[i][j] = 'O'