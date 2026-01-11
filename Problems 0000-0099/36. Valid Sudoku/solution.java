class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Set<String> seen = new HashSet();

        // for (int i = 0; i < board.length; i++) {
        //     for (int j = 0; j < board[i].length; j++) {
        //         if (board[i][j] == '.') continue;
        //         if (!seen.add(board[i][j] + " in row " + i) ||
        //             !seen.add(board[i][j] + " in col " + j) ||
        //             !seen.add(board[i][j] + " in block " + i / 3 + "-" + j / 3)) {
        //                 return false;
        //             }
        //     }
        // }

        // return true;

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] squares = new int[9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') continue;
                int val = board[i][j] - '0';
                int mask = (1 << val);
                int block = (i / 3) * 3 + j / 3;
                
                if ((rows[i] & mask) != 0 ||
                    (cols[j] & mask) != 0 ||
                    (squares[block] & mask) != 0) {
                    return false;
                }
                
                rows[i] |= mask;
                cols[j] |= mask;
                squares[block] |= mask;
            }
        }

        return true;
    }
}