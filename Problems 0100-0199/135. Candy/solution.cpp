class Solution {
    public:
        int candy(vector<int>& ratings) {
            // peaks and valleys
            // whenever we reach a valley, it should always be 1
            // and we should always climb up by one until we reach the peak
            // the peak needs to be readjusted if the slope down needs it
            // to be more
            int n = ratings.size();
            int res = n; // minimum 1 candy per child
            int i = 1;
            while (i < n) {
                if (ratings[i] == ratings[i-1]) {
                    i++;
                    continue;
                }
    
                int peak = 0;
                while (i < n && ratings[i] > ratings[i-1]) {
                    peak++;
                    res += peak;
                    i++;
    
                    if (i == n) return res;
                }
    
                int valley = 0;
                while (i < n && ratings[i] < ratings[i-1]) {
                    valley++;
                    res += valley;
                    i++;
                }
    
                res -= min(peak, valley);
    
            }
    
            return res;
        }
    };