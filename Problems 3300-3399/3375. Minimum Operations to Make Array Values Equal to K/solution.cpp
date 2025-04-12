class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int minVal = INT_MAX;
        unordered_set<int> seen;

        for (int& num : nums) {
            seen.insert(num);
            minVal = min(minVal, num);
        }
        // values can only be decreased not increased
        // so k has to be at least minVal or less
        if (minVal < k) return -1;

        int res = seen.size();
        if (seen.find(k) != seen.end()) res--;
        return res;
    }
};