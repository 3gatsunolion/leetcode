class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        int n = nums.size();

        // differ from each string by at least one bit
        string res;
        for (int i = 0; i < n; i++) {
            res += nums[i][i] == '0' ? '1' : '0';
        }
        return res;

        // int n = nums.size();
        // unordered_set<string> binaries(nums.begin(), nums.end());

        // string curr;
        // return findBinaryString(0, n, binaries, curr);
    }

    string findBinaryString(int i, int& n, unordered_set<string>& nums, string& curr) {
        if (i == n) return nums.find(curr) == nums.end() ? curr : "";
        // 0
        curr.push_back('0');
        string res = findBinaryString(i+1, n, nums, curr);
        if (res.length() == n) return res;
        curr.pop_back();
        // 1
        curr.push_back('1');
        res = findBinaryString(i+1, n, nums, curr);
        if (res.length() == n) return res;
        curr.pop_back();
        return "";
    }
};