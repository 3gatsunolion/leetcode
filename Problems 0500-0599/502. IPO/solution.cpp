class Solution {
    public:
        int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
            int n = profits.size();
            vector<pair<int, int>> projects;
            for (int i = 0; i < n; i++) {
                projects.emplace_back(profits[i], capital[i]);
            }
    
            sort(projects.begin(), projects.end(), [] (const pair<int, int>& a, const pair<int, int>& b) {
                return a.second < b.second;
            });
    
            priority_queue<int, vector<int>> maxHeap;
    
            int i = 0;
            while (k--) {
                while (i < n && projects[i].second <= w) {
                    maxHeap.emplace(projects[i].first);
                    i++;
                }
    
                if (maxHeap.empty()) break;
    
                int profit = maxHeap.top();
                maxHeap.pop();
                w += profit;
            }
            return w;
        }
    };