class Solution {
    public:
        int maxCandies(vector<int>& status, vector<int>& candies, vector<vector<int>>& keys, vector<vector<int>>& containedBoxes, vector<int>& initialBoxes) {
            int n = status.size();
            vector<bool> have(n, false);
            queue<int> q;
    
            for (int box : initialBoxes) {
                if (status[box]) {
                    q.push(box);
                }
                have[box] = true;
            }
            int res = 0;
            while (!q.empty()) {
                int box = q.front();
                res += candies[box];
                q.pop();
    
                for (int key : keys[box]) {
                    if (have[key] && !status[key]) {
                        q.push(key);
                    }
                    status[key] = 1;
                }
    
                for (int contained : containedBoxes[box]) {
                    if (status[contained]) {
                        q.push(contained);
                    }
                    have[contained] = true;
                }
            }
            return res;
        }
    };