class Solution {
    public:
        bool searchMatrix(vector<vector<int>>& matrix, int target) {
            int m = matrix.size();
            int n = matrix[0].size();
    
            int lo = 0;
            int hi = m*n-1;
    
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (matrix[mid/n][mid % n] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
    
            return matrix[lo/n][lo%n] == target;
        }
    };