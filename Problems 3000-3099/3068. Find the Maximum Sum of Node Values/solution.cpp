class Solution {
    public:
        long long maximumValueSum(vector<int>& nums, int k, vector<vector<int>>& edges) {
            long long best = 0;
            long long count = 0;
            long long sacrifice = INT_MAX;
            for (int num : nums) {
                best += max(num ^ k, num);
                count += (num ^ k) > num;
                sacrifice = min(sacrifice, 1LL*abs(num - (num ^ k)));
            }
            return count % 2 == 0 ? best : best - sacrifice;
        }
    };