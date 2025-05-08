class Solution {
    vector<int> DIRS{0, -1, 0, 1, 0};
public:
    int minTimeToReach(vector<vector<int>>& moveTime) {
        int n = moveTime.size();
        int m = moveTime[0].size();

        vector<vector<int>> minTime(n, vector<int>(m, INT_MAX));
        minTime[0][0] = 0;

        priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<tuple<int, int, int, int>>> minHeap;
        minHeap.emplace(0, 1, 0, 0);

        while (!minHeap.empty()) {
            auto [time, timeToMove, x, y] = minHeap.top();
            minHeap.pop();

            if (minTime[x][y] < time) continue;

            if (x == n - 1 && y == m - 1) return time;

            for (int i = 0; i < 4; i++) {
                int dx = x + DIRS[i];
                int dy = y + DIRS[i+1];

                if (dx < 0 || dy < 0 || dx == n || dy == m) continue;

                int waitTime = max(0, moveTime[dx][dy] - time);
                int newTime = time + waitTime + timeToMove;

                if (newTime < minTime[dx][dy]) {
                    minTime[dx][dy] = newTime;
                    minHeap.emplace(newTime, 3 - timeToMove, dx, dy);
                }
            }
        }

        return -1;
    }
};