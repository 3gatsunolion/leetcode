class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        long long totalCandies = accumulate(candies.begin(), candies.end(), 0ll);

        if (totalCandies < k) return 0;

        long long lo = 1, hi = totalCandies / k;
        long long res = 1;
        while (lo <= hi) {
            long long mid = lo + (hi - lo) / 2;
            if (canGiveKCandyEach(candies, mid, k)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    bool canGiveKCandyEach(vector<int>& candies, long long k, long long numChildren) {
        for (int pile : candies) {
            long long numSplit = pile / k;
            if (numSplit >= numChildren) return true;
            numChildren -= numSplit;
        }
        return false;
    }
};