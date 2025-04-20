class Solution {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        string target = "123450";
        string initial = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                // initial += board[i][j] + '0';
                initial += to_string(board[i][j]);
            }
        }
        vector<vector<int>> goTo = {
            {1, 3},
            {0, 2, 4},
            {1, 5},
            {0, 4},
            {1, 3, 5},
            {2, 4}
        };
        deque<pair<int, string>> q;
        set<string> seen;
        q.push_back({initial.find('0'), initial});
        seen.insert(initial);
        int moves = 0;
        while (!q.empty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                pair<int, string> p = q.front();
                int zeroPos = p.first;
                string state = p.second;
                q.pop_front();

                if (state == target) {
                    return moves;
                }

                for (int i : goTo[zeroPos]) {
                    string next = state;
                    swap(next[i], next[zeroPos]);
                    if (!seen.count(next)) {
                        q.push_back({i, next});
                        seen.insert(next);
                    }
                }

                
            }
            moves++;
        }
        return -1;
    }
};