class Solution {
public:
    vector<int> closestPrimes(int left, int right) {
        // sieve:
        // 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26
        // 3, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39
        // 5, 25, 30, 35, 40, 45, 50, 55
        vector<bool> sieve(right+1, true);
        sieve[0] = sieve[1] = false;
        for (long long num = 2; num <= right; num++) {
            if (!sieve[num]) continue;
            for (long long nonprime = num*num; nonprime <= right; nonprime += num) {
                sieve[nonprime] = false;
            }
        }

        vector<int> res;
        int prevPrime = -1;
        for (int num = max(2, left); num <= right; num++) {
            if (!sieve[num]) continue;
            if (res.size() < 2) {
                res.push_back(num);
            } else {
                int prevDiff = res[1] - res[0];
                int currDiff = num - prevPrime;
                if (currDiff < prevDiff) {
                    res[0] = prevPrime;
                    res[1] = num;
                }
            }
            prevPrime = num;
            if (res.size() == 2 && (res[1] - res[0]) <= 2) {
                return res;
            }
        }
        if (res.size() < 2) return {-1, -1};
        return res;
    }
};