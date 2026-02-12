class Solution {
    public:
        int countLargestGroup(int n) {
            unordered_map<int, int> groupCount;
            int maxGroupSize = 0;
            int res = 0;
            for (int num = 1; num <= n; num++) {
                int sumDigits = digitSum(num);
                groupCount[sumDigits]++;
                if (groupCount[sumDigits] > maxGroupSize) {
                    maxGroupSize = groupCount[sumDigits];
                    res = 1;
                } else if (groupCount[sumDigits] == maxGroupSize) {
                    res++;
                }
            }
            return res;
        }
    
        int digitSum(int n) {
            int res = 0;
            while (n > 0) {
                res += n % 10;
                n /= 10;
            }
            return res;
        }
    };