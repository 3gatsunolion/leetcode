class Solution {
public:
    vector<int> DIRECTIONS = {0, -1, 0, 1, 0};
    int minimumObstacles(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        deque<vector<int>> q;

        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[0][0] = 0;
        q.push_back({0, 0});

        while (!q.empty()) {
            const vector<int>& coord = q.front();
            int x = coord[0], y = coord[1];
            q.pop_front();
            // if (x == m - 1 && y == n - 1) {
            //     return dist[x][y];
            // }

            for (int i = 0; i < 4; i++) {
                int dx = x + DIRECTIONS[i], dy = y + DIRECTIONS[i+1];
                if (dx < 0 || dy < 0 || dx >= m || dy >= n || dist[dx][dy] != INT_MAX) {
                    continue;
                }
                dist[dx][dy] = dist[x][y] + grid[dx][dy];
                if (grid[dx][dy]) {
                    q.push_back({dx, dy});
                } else {
                    // no obstacle, so same dist as x, y
                    // so push to front
                    q.push_front({dx, dy});
                }
            }
        }
        return dist[m-1][n-1];
    }

    int minimumObstaclesDijkstra(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> cost(m, vector<int>(n, INT_MAX));
        cost[0][0] = 0; // 0,0 is starting point and always 0
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> minHeap;
        minHeap.push({0, 0, 0}); // dist, row, col
        while (!minHeap.empty()) {
            const vector<int>& state = minHeap.top();
            int remove = state[0], row = state[1], col = state[2];
            minHeap.pop();
            // stale data
            if (cost[row][col] < remove) {
                continue;
            }
            if (row == m-1 && col == n-1) {
                return remove;
            }

            for (int i = 0; i < 4; i++) {
                int x = row + DIRECTIONS[i];
                int y = col + DIRECTIONS[i+1];
                if (x < 0 || y < 0 || x >= m || y >= n) {
                    continue;
                }
                int newCost = remove + grid[x][y];
                if (cost[x][y] <= newCost) continue;
                cost[x][y] = newCost;
                minHeap.push({newCost, x, y});
            }
        }
        return -1;
    }
};