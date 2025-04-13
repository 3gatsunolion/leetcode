class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<vector<int>> graph(n);
        vector<int> dp(n), answer;
        // fill in initial dist, 0, 1, ... n-1
        iota(dp.begin(), dp.end(), 0);
        for (const vector<int>& query : queries) {
            // dest -> src
            graph[query[1]].push_back(query[0]);

            for (int i = query[1]; i < n; i++) {
                dp[i] = min(dp[i], dp[i-1] + 1);
                for (int city : graph[i]) {
                    dp[i] = min(dp[i], 1 + dp[city]);
                }
            }
            answer.push_back(dp[n-1]);
        }
        return answer;
    }

    vector<int> shortestDistanceAfterQueriesBFS(int n, vector<vector<int>>& queries) {
        vector<vector<int>> graph(n);
        for (int i = 0; i < n - 1; i++) {
            graph[i].push_back(i+1);
        }

        vector<int> answer;
        for (vector<int>& query : queries) {
            graph[query[0]].push_back(query[1]);
            answer.push_back(bfs(0, n-1, graph));
        }
        return answer;
    }

    int bfs(int start, int end, const vector<vector<int>>& graph) {
        queue<int> q;
        q.push(start);
        unordered_set<int> visited;
        visited.insert(start);
        int dist = 0;
        while (!q.empty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int city = q.front();
                q.pop();
                if (city == end) {
                    return dist;
                }

                for (const int& nextCity : graph[city]) {
                    if (!visited.count(nextCity)) {
                        q.push(nextCity);
                        visited.insert(nextCity);
                    }
                }
            }
            dist++;
        }
        return -1;
    }

    vector<int> shortestDistanceAfterQueriesDijkstra(int n, vector<vector<int>>& queries) {
        vector<vector<int>> graph(n);
        for (int i = 0; i < n - 1; i++) {
            graph[i].push_back(i+1);
        }
        vector<int> answer;
        for (vector<int>& query : queries) {
            graph[query[0]].push_back(query[1]);
            answer.push_back(dijkstra(0, n-1, n, graph));
        }
        return answer;
    }

    int dijkstra(int start, int end, int& n, vector<vector<int>>& graph) {
        // minheap
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;
        pq.push({0, start});
        vector<int> cost(n, INT_MAX);
        cost[start] = 0;

        while (!pq.empty()) {
            vector<int> el = pq.top();
            int dist = el[0], city = el[1];
            pq.pop();

            if (cost[city] < dist) {
                continue;
            }

            if (city == end) {
                return dist;
            }

            for (const int& nextCity : graph[city]) {
                if (cost[nextCity] <= dist+1) {
                    continue;
                }
                cost[nextCity] = dist+1;
                pq.push({dist+1, nextCity});
            }
        }
        return -1;
    }
};