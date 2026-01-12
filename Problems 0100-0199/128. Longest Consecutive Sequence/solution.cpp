class Solution {
    public:
        int longestConsecutive(vector<int>& nums) {
            unordered_set<int> unique(nums.begin(), nums.end());
            int res = 0;
            for (int num : unique) {
                // if num is smallest in its consecutive path
                int cand = num;
                if (unique.find(cand-1) == unique.end()) {
                    int count = 1;
                    while (unique.find(cand+1) != unique.end()) {
                        cand++;
                        count++;
                    }
                    res = max(res, count);
                }
            }
    
            return res;
    
        }
    };