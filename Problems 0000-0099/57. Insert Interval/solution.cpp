class Solution {
    public:
        vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
            int n = intervals.size();
            int i = 0;
            vector<vector<int>> res;
    
            while (i < n && intervals[i][1] < newInterval[0]) {
                res.push_back(intervals[i++]);
            }
    
            int start = newInterval[0];
            int end = newInterval[1];
            while (i < n && intervals[i][0] <= newInterval[1]) {
                start = min(intervals[i][0], start);
                end = max(intervals[i][1], end);
                i++;
            }
            res.push_back({start, end});
    
            while (i < n) {
                res.push_back(intervals[i++]);
            }
            return res;
        }
    };