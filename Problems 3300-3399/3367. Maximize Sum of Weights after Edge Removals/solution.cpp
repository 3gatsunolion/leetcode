class Solution {
public:
    long long maximizeSumOfWeights(vector<vector<int>>& edges, int k) {
        int n = edges.size() + 1;
        vector<vector<int>> wt(n), vtx(n);
        for (vector<int> edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            vtx[u].push_back(v);
            wt[u].push_back(w);
            vtx[v].push_back(u);
            wt[v].push_back(w);
        }

        return dfs(0, wt, vtx, k, -1).second;
    }

    pair<long long, long long> dfs(int node, vector<vector<int>>& wt, vector<vector<int>>& v, int k, int prev) {
        long long maxKMinusOne = 0, maxK = 0;
        vector<int> diff;
        for (int i = 0; i < v[node].size(); i++) {
            if (v[node][i] == prev) {
                continue;
            }
            pair<long long, long long> ans = dfs(v[node][i], wt, v, k, node);
            // add nextNodes sum with at most k edges
            // this value during this for loop is max values
            // when not connecting with curr node
            maxKMinusOne += ans.second;
            // res.second > res.first + w, then no point
            // connecting it
            // if res.first+w > res.second, we could
            // potentially connect, but take highest k
            diff.push_back(max(0LL, ans.first + wt[node][i] - ans.second));
        }
        // Quick select
        if (diff.size() >= k) {
            nth_element(diff.begin(), diff.begin() + k - 1, diff.end(), greater<int>());
        }
        for (int i = 0; i < k - 1 && i < diff.size(); ++i) {
            maxKMinusOne += diff[i];
        }

        maxK = diff.size() >= k ? maxKMinusOne + diff[k-1] : maxKMinusOne;
        return {maxKMinusOne, maxK};
    }
};