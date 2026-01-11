class Solution {
    public void solveSudoku(char[][] board) {
        int[][] seen = new int[3][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    markBoard(board, i, j, board[i][j] - '0', seen);
                }
            }
        }

        solve(board, 0, 0, seen);
    }

    private boolean solve(char[][] board, int row, int col, int[][] seen) {
        if (row == 9) return true;
        if (col == 9) return solve(board, row + 1, 0, seen);
        if (board[row][col] != '.') return solve(board, row, col + 1, seen);

        for (int i = 1; i <= 9; i++) {
            if (isValid(board, row, col, i, seen)) {
                markBoard(board, row, col, i, seen);
                if (solve(board, row, col + 1, seen)) return true;
                unmarkBoard(board, row, col, seen);
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, int val, int[][] seen) {
        int mask = 1 << val;
        int block = (row / 3) * 3 + (col / 3);
        if ((seen[0][row] & mask) != 0 ||
            (seen[1][col] & mask) != 0 ||
            (seen[2][block] & mask) != 0) {
                return false;
            }
        return true;
    }

    private void markBoard(char[][] board, int row, int col, int val, int[][] seen) {
        board[row][col] = (char) (val + '0');
        int mask = 1 << val;
        int block = (row / 3) * 3 + (col / 3);
        seen[0][row] |= mask;
        seen[1][col] |= mask;
        seen[2][block] |= mask;
    }

    private void unmarkBoard(char[][] board, int row, int col, int[][] seen) {
        int val = board[row][col] - '0';
        int mask = 1 << val;
        int block = (row / 3) * 3 + (col / 3);
        // seen[0][row] ^= mask;
        // seen[1][col] ^= mask;
        // seen[2][block] ^= mask;
        seen[0][row] &= ~mask;
        seen[1][col] &= ~mask;
        seen[2][block] &= ~mask;
        board[row][col] = '.';
    }
}