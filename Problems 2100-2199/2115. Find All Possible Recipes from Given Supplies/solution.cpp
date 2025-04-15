class Solution {
public:
    vector<string> findAllRecipes(vector<string>& recipes, vector<vector<string>>& ingredients, vector<string>& supplies) {
        unordered_set<string> suppliesIHave(supplies.begin(), supplies.end());

        int numRecipes = recipes.size();
        unordered_map<string, vector<string>> graph;
        unordered_map<string, int> indegree;

        for (int i = 0; i < numRecipes; i++) {
            indegree[recipes[i]] = 0;
        }

        for (int i = 0; i < numRecipes; i++) {
            for (string& ingredient : ingredients[i]) {
                if (suppliesIHave.find(ingredient) == suppliesIHave.end()) {
                    graph[ingredient].push_back(recipes[i]);
                    indegree[recipes[i]]++;
                }
            }
        }

        queue<string> q;
        for (int i = 0; i < numRecipes; i++) {
            if (indegree[recipes[i]] == 0) q.push(recipes[i]);
        }

        vector<string> res;
        while (!q.empty()) {
            string node = q.front();
            q.pop();
            res.push_back(node);
            for (string& adj : graph[node]) {
                indegree[adj]--;
                if (indegree[adj] == 0) q.push(adj);
            }
        }
        return res;
    }

    vector<string> findAllRecipesDFS(vector<string>& recipes, vector<vector<string>>& ingredients, vector<string>& supplies) {
        // topological sort, if there is a cycle then it's
        // not possible or if missing ingredient
        unordered_set<string> suppliesIHave(supplies.begin(), supplies.end());

        int numRecipes = recipes.size();
        unordered_map<string, vector<string>> graph;
        for (int i = 0; i < numRecipes; i++) {
            for (string& ingredient : ingredients[i]) {
                graph[recipes[i]].push_back(ingredient);
            }
        }

        unordered_map<string, int> visited;
        vector<string> res;
        for (int i = 0; i < numRecipes; i++) {
            if (!hasCycle(recipes[i], graph, suppliesIHave, visited)) {
                res.push_back(recipes[i]);
            }
        }
        return res;
    }

    bool hasCycle(string& node, unordered_map<string, vector<string>>& graph, unordered_set<string>& supplies, unordered_map<string, int>& visited) {
        if (graph.find(node) == graph.end()) return true;
        if (visited[node] == -1) return true;
        if (visited[node] == 1) return false;
        visited[node] = -1;
        for (string& adj : graph[node]) {
            if (supplies.find(adj) == supplies.end() && hasCycle(adj, graph, supplies, visited)) return true;
        }
        visited[node] = 1;
        return false;
    }
};