class UnionFind {
private:
    vector<int> parent;
    vector<int> size;
public:
    UnionFind(int n): parent(n), size(n, 1) {
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

    void unionBySize(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (size[rootX] < size[rootY]) swap(rootX, rootY);

        parent[rootY] = rootX;
        size[rootX] += size[rootY];
    }

    int getSize(int x) {
        int rootX = find(x);

        return size[rootX];
    }
};

class Solution {
    const vector<int> DIRS{0, 1, 0, -1, 0};
public:
    vector<int> maxPoints(vector<vector<int>>& grid, vector<int>& queries) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> sortedVals;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sortedVals.emplace_back(grid[i][j], i, j);
            }
        }

        sort(sortedVals.begin(), sortedVals.end());

        vector<pair<int, int>> sortedQueries;
        for (int i = 0; i < queries.size(); i++) {
            sortedQueries.emplace_back(queries[i], i);
        }
        sort(sortedQueries.begin(), sortedQueries.end());

        vector<int> ans(queries.size(), 0);
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        int index = 0;
        UnionFind uf(m*n);
        for (auto& [queryVal, queryIndex] : sortedQueries) {
            while (index < m*n && get<0>(sortedVals[index]) < queryVal) {
                auto [val, x, y] = sortedVals[index];
                visited[x][y] = true;

                for (int i = 0; i < 4; i++) {
                    int dx = x + DIRS[i];
                    int dy = y + DIRS[i+1];

                    if (dx < 0 || dy < 0 || dx == m || dy == n || !visited[dx][dy]) continue;

                    uf.unionBySize(x*n+y, dx*n+dy);
                }
                index++;
            }

            if (visited[0][0]) {
                ans[queryIndex] = uf.getSize(0);
            }
        }

        return ans;
    }
    vector<int> maxPointsHeap(vector<vector<int>>& grid, vector<int>& queries) {
        // optimal order of traversing
        int m = grid.size(), n = grid[0].size();

        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> queryHeap;
        for (int i = 0; i < queries.size(); i++) {
            queryHeap.emplace(queries[i], i);
        }

        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> gridHeap;
        gridHeap.emplace(grid[0][0], 0, 0);
        vector<int> order;
        grid[0][0] = 0; // mark visited
        int count = 0;
        vector<int> ans(queries.size(), 0);
        while (!queryHeap.empty()) {
            auto [queryVal, queryIndex] = queryHeap.top();
            queryHeap.pop();

            while (!gridHeap.empty() && get<0>(gridHeap.top()) < queryVal) {
                auto [val, x, y] = gridHeap.top();
                gridHeap.pop();

                count++;

                for (int i = 0; i < 4; i++) {
                    int dx = x + DIRS[i];
                    int dy = y + DIRS[i+1];

                    if (dx < 0 || dy < 0 || dx == m || dy == n || grid[dx][dy] == 0) continue;

                    gridHeap.emplace(grid[dx][dy], dx, dy);
                    grid[dx][dy] = 0;
                }
            }
            ans[queryIndex] = count;
        }

        return ans;
        
    }
};