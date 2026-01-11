class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        rows = [[False]*9 for _ in range(9)]
        cols = [[False]*9 for _ in range(9)]
        squares = [[False]*9 for _ in range(9)]

        for i in range(9):
            for j in range(9):
                if board[i][j] == ".":
                    continue

                num = ord(board[i][j])-ord("1")
                key = i // 3 * 3 + j // 3
                if rows[i][num] or cols[j][num] or squares[key][num]:
                    return False
                rows[i][num] = cols[j][num] = squares[key][num] = True
        
        return True