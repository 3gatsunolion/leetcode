class Solution {
public:
    long long maxMatrixSum(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int minAbsNum = INT_MAX, numNegative = 0;
        long absoluteSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) {
                    numNegative++;
                }
                minAbsNum = abs(matrix[i][j]) < minAbsNum ? abs(matrix[i][j]) : minAbsNum;
                absoluteSum += abs(matrix[i][j]);
            }
        }

        if (numNegative % 2 == 0) {
            return absoluteSum;
        } else {
            // minus twice, once to offset when we added
            // to absoluteSum
            return absoluteSum - (2*minAbsNum);
        }
    }
};