class Solution {
    public:
        void gameOfLife(vector<vector<int>>& board) {
            int m = board.size();
            int n = board[0].size();
    
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int liveNeighbors = countLiveNeighbors(board, i, j);
                    if (board[i][j] & 1) {
                        if (liveNeighbors == 2 || liveNeighbors == 3) {
                            board[i][j] = 3;
                        }
                    } else if (liveNeighbors == 3) {
                        board[i][j] = 2;
                    }
                }
            }
    
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] >>= 1;
                }
            }
        }
    
        int countLiveNeighbors(vector<vector<int>>& board, int i, int j) {
            int m = board.size();
            int n = board[0].size();
            int count = 0;
            for (int r = max(0, i-1); r < min(i+2, m); r++) {
                for (int c = max(0, j-1); c < min(j+2, n); c++) {
                    if (r == i && c == j) continue;
                    if (board[r][c] & 1) count++;
                }
            }
            return count;
        }
    };