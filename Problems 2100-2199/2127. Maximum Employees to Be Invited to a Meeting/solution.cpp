class Solution {
public:
    int maximumInvitations(vector<int>& favorite) {
        int n = favorite.size();
        vector<vector<int>> revGraph(n);
        for (int node = 0; node < n; node++) {
            revGraph[favorite[node]].push_back(node);
        }
        // if a person is more than 2 people's favorite
        // then the rest of those people can't be in the meeting
        // best case: everyone only has 1 other person who likes
        // them so it's just a circle: 0->1->2->3->4->0
        // there can't be 2 cycles which are connected to
        // each other
        // one set/component will have exactly one cycle.
        // 1st type of cycle: two nodes that point to one
        // another and then two branches that point to each one.
        // since two branches don't connect we can have more than
        // one of these components
        // 2: if there's is a cycle that is longer than two nodes
        // then we can only have the circle (no branch
        // pointing to a node in this circle can be included)
        // find the longest circle component

        vector<int> dist(n, -1);
        int cycle1Sum = 0;
        // first type of cycle: two nodes in cycle
        for (int node = 0; node < n; node++) {
            if (dist[node] != -1) continue;
            if (favorite[favorite[node]] == node) {
                // mark left and right nodes are visited
                // so we don't visit it later
                dist[node] = dist[favorite[node]] = 0;
                // go through the nodes that point to
                // each node
                int left = 0;
                for (int adj : revGraph[node]) {
                    if (adj == favorite[node]) continue;
                    left = max(left, dfs1(adj, revGraph, dist));
                }

                int right = 0;
                for (int adj : revGraph[favorite[node]]) {
                    if (adj == node) continue;
                    right = max(right, dfs1(adj, revGraph, dist));
                }

                cycle1Sum += left + right + 2;
            }
        }

        // case 2/type cycle 2
        int cycle2 = 0;
        for (int node = 0; node < n; node++) {
            if (dist[node] != -1) continue;
            auto [entryPoint, d, circleTraversed] = dfs2(node, favorite, dist);

            if (circleTraversed) {
                cycle2 = max(cycle2, d);
            }
        }

        return max(cycle1Sum, cycle2);
    }

    int dfs1(int node, vector<vector<int>>& graph, vector<int>& dist) {
        if (dist[node] != -1) return dist[node];

        int res = 0;
        for (int adj : graph[node]) {
            res = max(res, dfs1(adj, graph, dist));
        }
        return dist[node] = 1 + res;
    }

    tuple<int, int, bool> dfs2(int node, vector<int>& graph, vector<int>& dist) {
        // entry point of cycle!
        if (dist[node] != -1) return {node, dist[node], false};
        dist[node] = 0; // mark as "in progress of being visited"

        auto [entryPoint, d, circleTraversed] = dfs2(graph[node], graph, dist);
        // if circle part already traversed, then this is not
        // part of the cycle and we don't need to go further
        if (circleTraversed) {
            return {entryPoint, d, true};
        }

        dist[node] = 1 + d;
        return {entryPoint, dist[node], node == entryPoint};
    }
};