class Solution {
public:
    vector<int> getFinalState(vector<int>& nums, int k, int multiplier) {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> minHeap;
        int n = nums.size();
        vector<int> res(n);

        for (int i = 0; i < n; i++) {
            minHeap.push({nums[i], i});
        }

        while (k--) {
            pair<int, int> el = minHeap.top();
            minHeap.pop();
            minHeap.push({el.first * multiplier, el.second});
        }

        while (!minHeap.empty()) {
            auto [num, i] = minHeap.top();
            minHeap.pop();
            res[i] = num;
        }
        return res;
    }
};