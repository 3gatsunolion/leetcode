class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<vector<int>> dp(n, vector<int>(n, 0));
        // max[i][j] -> max point to get on left half 
        // when you split array from [i:k], k <= j
        // right half if i > j (saves space)
        vector<vector<int>> maxSplit(n, vector<int>(n, 0));

        for (int i = 0; i < n; i++) {
            maxSplit[i][i] = stoneValue[i];
        }

        for (int start = n-2; start >= 0; start--) {
            int k = start, total = stoneValue[start];
            int leftHalfSum = stoneValue[start];
            for (int end = start+1; end < n; end++) {
                total += stoneValue[end];
                while (leftHalfSum*2 < total) {
                    k++;
                    leftHalfSum += stoneValue[k];
                }

                // equal parts
                if ((leftHalfSum << 1) == total) {
                    dp[start][end] = max(maxSplit[start][k], maxSplit[end][k+1]);
                } else {
                    dp[start][end] = max(k == start ? 0 : maxSplit[start][k-1], k == end ? 0 : maxSplit[end][k+1]);
                }

                maxSplit[start][end] = max(maxSplit[start][end-1], dp[start][end] + total);
                maxSplit[end][start] = max(maxSplit[end][start+1], dp[start][end] + total);
            }
        }
        return dp[0][n-1];
    }
    // int stoneGameV(vector<int>& stoneValue) {
    //     int n = stoneValue.size();
    //     vector<vector<int>> dp(n, vector<int>(n, 0));
    //     // left[i][j] -> max point to get on left half 
    //     // when you split array from [i:k], k <= j
    //     vector<vector<int>> left(n, vector<int>(n, 0));
    //     vector<vector<int>> right(n, vector<int>(n, 0));

    //     vector<int> prefixSums(n);
    //     for (int i = 0; i < n; i++) {
    //         prefixSums[i] = stoneValue[i] + (i > 0 ? prefixSums[i-1] : 0);
    //         left[i][i] = right[i][i] = stoneValue[i];
    //     }

    //     for (int len = 2; len <= n; len++) {
    //         for (int start = 0; start <= n - len; start++) {
    //             int end = start + len - 1;

    //             // from [start, end] find critical point where
    //             // left > right (find the smallest point in
    //             // which we get rid of larger left half, so we
    //             // know at which point a number cannot be included
    //             // in left half of split)
    //             // example: [98,77,24,49,6,12,2,44,51,96]->459
    //             // split: [44.51,96]->191
    //             // split: [44,51] -> 95
    //             // 191+95+44=330
    //             int totalSum = prefixSums[end]-prefixSums[start]+stoneValue[start];
    //             // int k = lower_bound(prefixSums.begin() + start, prefixSums.begin() + end, ceil(totalSum / 2), [&](const int& a, const int& b) {
    //             //     return (a-prefixSums[start]+stoneValue[start]) < (b-prefixSums[start]+stoneValue[start]);
    //             // }) - prefixSums.begin();
    //             int k = search(prefixSums, stoneValue, start, end);

    //             int leftHalfSum = prefixSums[k]-prefixSums[start]+stoneValue[start];

    //             // equal parts
    //             if ((leftHalfSum << 1) == totalSum) {
    //                 dp[start][end] = max(left[start][k], right[k+1][end]);
    //             } else {
    //                 dp[start][end] = max(k == start ? 0 : left[start][k-1], k == end ? 0 : right[k+1][end]);
    //             }

    //             left[start][end] = max(left[start][end-1], dp[start][end] + totalSum);
    //             right[start][end] = max(right[start+1][end], dp[start][end] + totalSum);
    //         }
    //     }
    //     return dp[0][n-1];
    // }

    // int search(vector<int>& prefixSums, vector<int>& stoneValue, int start, int end) {
    //     int total = prefixSums[end]-prefixSums[start]+stoneValue[start];
    //     int lo = start, hi = end;
    //     while (lo < hi) {
    //         int mid = lo + (hi - lo) / 2;
    //         if (((prefixSums[mid]-prefixSums[start]+stoneValue[start]) << 1) < total) {
    //             lo = mid + 1;
    //         } else {
    //             hi = mid;
    //         }
    //     }
    //     return lo;
    // }

    // int stoneGameV(vector<int>& stoneValue) {
    //     int n = stoneValue.size();
    //     vector<vector<int>> dp(n, vector<int>(n, 0));

    //     vector<int> prefixSums(n);
    //     for (int i = 0; i < n; i++) {
    //         prefixSums[i] = stoneValue[i] + (i > 0 ? prefixSums[i-1] : 0);
    //     }

    //     for (int len = 2; len <= n; len++) {
    //         for (int start = 0; start <= n - len; start++) {
    //             int end = start + len - 1;
    //             int score = 0;
    //             for (int i = start; i < end; i++) {
    //                 int left = prefixSums[i]-prefixSums[start]+stoneValue[start];
    //                 int right = prefixSums[end]-prefixSums[i];

    //                 if (left > right) {
    //                     score = max(score, right + dp[i+1][end]);
    //                 } else if (left < right) {
    //                     score = max(score, left + dp[start][i]);
    //                 } else {
    //                     score = max(score, left + max(dp[start][i], dp[i+1][end]));
    //                 }
    //             }
    //             dp[start][end] = score;
    //         }
    //     }
    //     return dp[0][n-1];
    // }
};