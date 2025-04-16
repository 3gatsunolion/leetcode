class Solution {
public:
    long long repairCars(vector<int>& ranks, int cars) {
        int minRank = *min_element(ranks.begin(), ranks.end());

        long long lo = minRank;
        long long hi = (long long)minRank*cars*cars;

        while (lo < hi) {
            long long mid = lo + (hi - lo) / 2;
            if (canRepairInTime(ranks, cars, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    bool canRepairInTime(vector<int>& ranks, int cars, long long maxTime) {
        for (int rank : ranks) {
            long long carsFixed = sqrt(maxTime / rank);
            if (carsFixed >= cars) return true;
            cars -= carsFixed;
        }
        return false;
    }
};