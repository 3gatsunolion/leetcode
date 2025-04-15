class Solution {
public:
    vector<int> rearrangeArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);
        int pos = 0, neg = 1;
        for (int num : nums) {
            if (num > 0) {
                res[pos] = num;
                pos += 2;
            }
            if (num < 0) {
                res[neg] = num;
                neg += 2;
            }
        }
        return res;
        // [3, 1, 2, 4, -1, -2, -3, -4]
        // 3, -1, 2, 4, 1, -2, -3, -4
        // 3, -1, 1, 4, 2, -2, -3, -4
        // 3, -1, 1, -2, 2, 4, -3, -4
    }
};