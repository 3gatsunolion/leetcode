class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        # 00 -> dead to dead
        # 01 -> live to dead
        # 10 -> dead to live
        # 11 -> live to live

        m = len(board)
        n = len(board[0])

        def count_live_neighbors(i, j):
            count = 0
            for r in range(max(0, i-1), min(i+2, m)):
                for c in range(max(0, j-1), min(j+2, n)):
                    if r == i and c == j:
                        continue
                    if board[r][c] & 1:
                        count += 1
            
            return count

        for i in range(m):
            for j in range(n):
                live_neighbors = count_live_neighbors(i, j)
                if board[i][j] & 1 == 1:
                    if live_neighbors == 2 or live_neighbors == 3:
                        board[i][j] = 3
                elif live_neighbors == 3:
                    board[i][j] = 2
            
        for i in range(m):
            for j in range(n):
                board[i][j] >>= 1