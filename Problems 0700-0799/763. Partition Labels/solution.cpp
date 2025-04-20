class Solution {
public:
    vector<int> partitionLabels(string s) {
        vector<int> lastSeen(26, 0);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastSeen[s[i]-'a'] = i;
        }

        vector<int> res;
        int prevEnd = -1;
        int currEnd = -1;
        for (int i = 0; i < n; i++) {
            currEnd = max(currEnd, lastSeen[s[i]-'a']);
            if (i == currEnd) {
                res.push_back(i-prevEnd);
                prevEnd = currEnd;
            }
        }
        return res;
    }
};