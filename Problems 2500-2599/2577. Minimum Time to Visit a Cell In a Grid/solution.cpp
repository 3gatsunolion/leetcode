class Solution {
public:
    int minimumTime(vector<vector<int>>& grid) {
        // Only time we can't make it to bottom-right
        // is if we're blocked from the starting position
        // otherwise we can just flipflop between previous
        // cells until we meet the wait time
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

        vector<int> dir = {0, -1, 0, 1, 0};
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> minHeap;
        minHeap.push({0, 0, 0});

        while (!minHeap.empty()) {
            const vector<int>& el = minHeap.top();
            int time = el[0], x = el[1], y = el[2];
            minHeap.pop();

            if (x == m - 1 && y == n - 1) {
                return time;
            }

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            for (int step = 0; step < 4; step++) {
                int dx = x + dir[step], dy = y + dir[step+1];
                if (dx < 0 || dy < 0 || dx >= m || dy >= n || visited[dx][dy]) {
                    continue;
                }
                // if grid[dx][dy] - time is even, then we have
                // to wait 1 more because of flip-flopping if
                // we have to wait
                int newTime = time + 1;
                if (grid[dx][dy] > time + 1) {
                    newTime = (grid[dx][dy] - time) % 2 == 0 ? 1 + grid[dx][dy] : grid[dx][dy];
                }
                minHeap.push({newTime, dx, dy});
            }
        }

        return -1;
    }
};