class Solution {
public:
    int countDays(int days, vector<vector<int>>& meetings) {
        // // sort by end meeting day
        // sort(meetings.begin(), meetings.end(), [](const vector<int>& m1, const vector<int>& m2) {
        //     return m1[1] < m2[1];
        // });
        sort(meetings.begin(), meetings.end());

        int count = 0;
        int n = meetings.size();
        int curr = 1;
        for (int i = 0; i < n; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            count += max(0, start - curr);
            curr = max(curr, end + 1);
            if (curr > days) break;
        }

        return count + days - curr + 1;
    }
};