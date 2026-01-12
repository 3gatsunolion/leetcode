class Solution {
    public:
        vector<int> DIRS{0, -1, 0, 1, 0};
        void solve(vector<vector<char>>& board) {
            int m = board.size();
            int n = board[0].size();
            
            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') bfs(i, 0, board);
    
                if (board[i][n-1] == 'O') bfs(i, n-1, board);
            }
    
            for (int j = 0; j < n; j++) {
                if (board[0][j] == 'O') bfs(0, j, board);
    
                if (board[m-1][j] == 'O') bfs(m-1, j, board);
            }
    
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == '#') board[i][j] = 'O';
                }
            }
        }
    
        void bfs(int i, int j, vector<vector<char>>& board) {
            int m = board.size();
            int n = board[0].size();
    
            deque<pair<int, int>> q;
            q.emplace_back(i, j);
            board[i][j] = '#';
    
            while (!q.empty()) {
                auto [x, y] = q.front();
                q.pop_front();
    
                for (int step = 0; step < 4; step++) {
                    int dx = x + DIRS[step];
                    int dy = y + DIRS[step+1];
    
                    if (dx < 0 || dy < 0 || dx == m || dy == n || board[dx][dy] != 'O') continue;
                    board[dx][dy] = '#';
                    q.emplace_back(dx, dy);
                }
            }
        }
    };