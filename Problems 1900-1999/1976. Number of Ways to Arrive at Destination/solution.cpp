class Solution {
public:
    const int mod = 1e9 + 7;
    int countPaths(int n, vector<vector<int>>& roads) {

        vector<vector<pair<int, int>>> graph(n);
        for (auto& road : roads) {
            graph[road[0]].push_back(make_pair(road[1], road[2]));
            graph[road[1]].push_back(make_pair(road[0], road[2]));
        }

        // dijkstra
        priority_queue<pair<long, int>, vector<pair<long, int>>, greater<>> minHeap;
        minHeap.push(make_pair(0, 0));
        vector<long> minTime(n, LONG_MAX);
        vector<long> ways(n);
        ways[0] = 1; // 1 way to get from 0 to 0
        minTime[0] = 0; // takes 0 time to get from 0 to 0
        while (!minHeap.empty()) {
            auto [time, node] = minHeap.top();
            minHeap.pop();

            // out of date
            if (minTime[node] < time) continue;

            for (auto& road : graph[node]) {
                int adj = road.first;
                long long newTime = (long long)road.second + time;

                if (newTime < minTime[adj]) {
                    minTime[adj] = newTime;
                    ways[adj] = ways[node];
                    minHeap.push(make_pair(newTime, adj));
                } else if (newTime == minTime[adj]) {
                    ways[adj] = (ways[adj] + ways[node]) % mod;
                }
            }
        }
        return ways[n-1];
    }
};