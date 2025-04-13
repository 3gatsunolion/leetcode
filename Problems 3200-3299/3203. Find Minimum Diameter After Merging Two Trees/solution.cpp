class Solution {
public:
    int minimumDiameterAfterMerge(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
    // 2 crucial properties of trees with undirected edges:
    // 1-connectedness (ie. between any 2 nodes in a tree 
    // there is exactly one path)
    // any node can serve as the root of the tree.

    // In a tree, the longest path must always occur between 
    // two leaf nodes, or at least between two nodes that are
    // maximally distant from each other in the tree.
    // The first BFS/DFS from A will give you one endpoint (B)
    // of this longest path, because we are "expanding out"
    // and encountering the farthest node.
    // Starting from B and performing a second BFS/DFS ensures
    // we find the other endpoint (C) of this longest path,
    // and thus the diameter of the tree.
        int d1 = diameter(edges1);
        int d2 = diameter(edges2);

        // larger half of each diameter plus one to connect
        // two trees together
        int d3 = ceil(d1 / 2.0) + ceil(d2 / 2.0) + 1;
        return max(d1, max(d2, d3));
    }

    int diameter(vector<vector<int>>& edges) {
        // fix the adj matrix since undirected
        int n = edges.size() + 1;
        vector<vector<int>> graph(n);
        for (vector<int>& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        // auto [node1, _] = bfs(0, n, graph);
        // auto [_, dist] = bfs(node1, n, graph);
        auto [node1, _] = dfs(0, -1, graph);
        auto [_, dist] = dfs(node1, -1, graph);

        return dist;
    }

    pair<int, int> dfs(int node, int parent, vector<vector<int>>& edges) {
        int maxNode = node, maxDist = 0;
        for (int adj : edges[node]) {
            if (adj == parent) continue;
            auto [v, d] = dfs(adj, node, edges);
            if (d + 1 > maxDist) {
                maxDist = d + 1;
                maxNode = v;
            }
        }
        return {maxNode, maxDist};
    }

    pair<int, int> bfs(int root, int& n, vector<vector<int>>& edges) {
        queue<int> q;
        q.push(root);

        vector<bool> visited(n, false);
        visited[root] = true;

        int dist = 0, farthest = root;
        while (!q.empty()) {
            int qn = q.size();
            for (int i = 0; i < qn; i++) {
                int node = q.front();
                q.pop();

                farthest = node;

                for (int adj : edges[node]) {
                    if (visited[adj]) continue;
                    visited[adj] = true;
                    q.push(adj);
                }
            }

            if (!q.empty()) dist++;

        }
        return {farthest, dist};
    }
};