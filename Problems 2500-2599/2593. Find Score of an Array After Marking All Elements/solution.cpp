class Solution {
public:
    long long findScore(vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> numsAndIndices(n);
        for (int i = 0; i < n; i++) {
            numsAndIndices[i] = {nums[i], i};
        }
        sort(numsAndIndices.begin(), numsAndIndices.end());
        vector<bool> marked(n, false);

        long long score = 0;
        for (auto& [num, i] : numsAndIndices) {
            if (marked[i]) continue;
            score += num;
            marked[i] = true;
            if (i > 0) marked[i-1] = true;
            if (i < n - 1) marked[i+1] = true;
        }
        return score;
    }

    long long findScore2(vector<int>& nums) {
        int n = nums.size();
        vector<bool> marked(n, false);
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> minHeap;

        for (int i = 0; i < n; i++) {
            minHeap.push({nums[i], i});
        }

        long long score = 0;
        while (!minHeap.empty()) {
            const vector<int>& el = minHeap.top();
            int num = el[0], i = el[1];
            minHeap.pop();

            if (marked[i]) continue;
            marked[i] = true;
            if (i > 0) marked[i-1] = true;
            if (i < n - 1) marked[i+1] = true;

            score += num;
        }
        return score;
    }
};