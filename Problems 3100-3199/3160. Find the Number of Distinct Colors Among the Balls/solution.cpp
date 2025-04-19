class Solution {
public:
    vector<int> queryResults(int limit, vector<vector<int>>& queries) {
        unordered_map<int, int> colors;
        unordered_map<int, int> count;

        vector<int> res;
        for (vector<int>& query : queries) {
            int ball = query[0], color = query[1];
            if (colors.find(ball) != colors.end()) {
                count[colors[ball]]--;
                if (count[colors[ball]] == 0) count.erase(colors[ball]);
            }
            count[color]++;
            colors[ball] = color;
            res.push_back(count.size());
        }

        return res;
    }
};