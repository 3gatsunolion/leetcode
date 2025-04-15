class Solution {
public:
    int maximumGood(vector<vector<int>>& statements) {
        // try all combinations: 111111 -> everyone is a good person
        int n = statements.size();
        int res = 0;
        for (int i = 1; i < 1 << n; i++) {
            if (isValid(i, n, statements)) {
                // res = max(res, numSetBits(i));
                res = max(res, __builtin_popcount(i));
            }
        }
        return res;
    }

    bool isValid(int& people, int& n, vector<vector<int>>& statements) {
        for (int i = 0; i < n; i++) {
            // check that all statements made by good person
            // checks out
            if (people & (1 << i)) {
                for (int j = 0; j < n; j++) {
                    if (statements[i][j] != 2 && bool(people & (1 << j)) != statements[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int numSetBits(int num) {
        int count = 0;
        while (num > 0) {
            count += num & 1;
            num >>= 1;
        }
        return count;
    }
};