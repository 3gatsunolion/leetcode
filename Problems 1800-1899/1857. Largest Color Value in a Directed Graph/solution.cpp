class Solution {
    public:
        int largestPathValue(string colors, vector<vector<int>>& edges) {
            int n = colors.length();
            vector<vector<int>> graph(n);
            vector<int> indegrees(n, 0);
            for (auto& edge : edges) {
                int a = edge[0];
                int b = edge[1];
                graph[a].push_back(b);
                indegrees[b]++;
            }
    
            // dp[i][j]: longest number of nodes with color j on path ending at i
            vector<vector<int>> dp(n, vector<int>(26, 0));
            queue<int> q;
            for (int node = 0; node < n; node++) {
                if (indegrees[node] == 0) {
                    q.push(node);
                    // since node is starting point (indegree of 0),
                    // its longest length path ending at itself it 1
                    dp[node][colors[node]-'a'] = 1;
                }
            }
    
            int res = 0;
            int seen = 0;
            while (!q.empty()) {
                int node = q.front();
                q.pop();
                seen++;
                res = max(res, *max_element(dp[node].begin(), dp[node].end()));
    
                for (int adj : graph[node]) {
                    for (int color = 0; color < 26; color++) {
                        // dp[adj][color] = max(dp[adj][color], dp[node][color] + (colors[adj]-'a' == color));
                        dp[adj][color] = max(dp[adj][color], dp[node][color]);
                    }
                    indegrees[adj]--;
                    if (indegrees[adj] == 0) {
                        q.push(adj);
                        dp[adj][colors[adj]-'a']++;
                    }
                }
            }
    
            return seen == n ? res : -1;
        }
    };