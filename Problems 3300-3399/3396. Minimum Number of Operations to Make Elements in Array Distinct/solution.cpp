class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        // Start from the back (since we can only delete
        // from the start). When we start from the back,
        // as soon as we see a duplicate, then we know
        // we must delete until that point, because up
        // until then, every element has been unique

        unordered_set<int> seen;
        int n = nums.size();
        for (int i = n-1; i >= 0; i--) {
            if (seen.find(nums[i]) != seen.end()) {
                return (i / 3) + 1;
            }
            seen.insert(nums[i]);
        }
        return 0;
    }
};