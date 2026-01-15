class Solution {
    public:
        vector<string> getLongestSubsequence(vector<string>& words, vector<int>& groups) {
            vector<string> res;
            int n = groups.size();
            int prev = -1;
            for (int i = 0; i < n; i++) {
                if (prev != groups[i]) {
                    res.push_back(words[i]);
                    prev = groups[i];
                }
            }
            return res;
        }
    };