class Solution {
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        vector<vector<int>> paths;
        int n = graph.size();
        vector<bool> visited(n, false);
        visited[0] = true;
        vector<int> path;
        path.push_back(0);
        findPaths(0, n-1, graph, visited, path, paths);
        return paths;
    }

    void findPaths(int src, int dest, vector<vector<int>>& graph, vector<bool>& visited, vector<int>& path, vector<vector<int>>& paths) {
        if (src == dest) {
            paths.push_back(path);
            return;
        }

        for (int node : graph[src]) {
            if (visited[node]) continue;
            visited[node] = true;
            path.push_back(node);
            findPaths(node, dest, graph, visited, path, paths);
            path.pop_back();
            visited[node] = false;
        }
    }
};