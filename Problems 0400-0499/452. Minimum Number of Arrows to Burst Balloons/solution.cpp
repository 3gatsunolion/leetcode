class Solution {
    public:
        int findMinArrowShots(vector<vector<int>>& points) {
            int n = points.size();
            if (n == 0) return 0;
    
            sort(points.begin(), points.end(), [] (const vector<int>& a, const vector<int>& b) {
                return a[1] < b[1];
            });
    
            int arrow = points[0][1];
            int res = 1;
    
            for (int i = 1; i < n; i++) {
                if (points[i][0] > arrow) {
                    res++;
                    arrow = points[i][1];
                }
            }
            return res;
        }
    };