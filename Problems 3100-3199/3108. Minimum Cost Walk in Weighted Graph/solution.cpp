class UnionFind {
private:
    // int n;
    vector<int> parent;
    vector<int> rank;
    vector<int> cost;
public:
    // -1 : 11111111 -> two's complement
    // UnionFind(int n) : n(n), parent(n), rank(n, 0), cost(n, -1) {
        UnionFind(int n) : parent(n), rank(n, 0), cost(n, -1) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void unionRank(int x, int y, int weight) {
        int rootX = find(x);
        int rootY = find(y);

        if (rank[rootX] < rank[rootY]) swap(rootX, rootY);

        parent[rootY] = rootX;
        if (rank[rootX] == rank[rootY]) rank[rootX]++;

        cost[rootX] = cost[rootY] = cost[rootX] & cost[rootY] & weight;
    }

    int getMinCost(int x, int y) {
        if (x == y) return 0;
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) return -1;

        return cost[rootX];
    }
};

class Solution {
public:
    vector<int> minimumCost(int n, vector<vector<int>>& edges, vector<vector<int>>& query) {
        // since we can walk same vertices any amount of time
        // we should find out connected components (which means)
        // we can traverse anywhere in that group of nodes
        // just take the bitwise and of the entire group
        // because it will always give us the minimum
        UnionFind uf(n);
        for (auto& edge : edges) {
            uf.unionRank(edge[0], edge[1], edge[2]);
        }

        vector<int> res;
        for (auto& q : query) {
            res.push_back(uf.getMinCost(q[0], q[1]));
        }

        return res;

    }
};