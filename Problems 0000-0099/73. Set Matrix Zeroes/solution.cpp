class Solution {
    public:
        void setZeroes(vector<vector<int>>& matrix) {
            int ROWS = matrix.size();
            int COLS = matrix[0].size();
            bool rowZero = false;
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        if (i == 0) {
                            rowZero = true;
                        } else {
                            matrix[i][0] = 0;
                        }
                    }
                }
            }
    
            for (int row = 1; row < ROWS; row++) {
                for (int col = 1; col < COLS; col++) {
                    if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                        matrix[row][col] = 0;
                    }
                }
            }
    
            if (matrix[0][0] == 0) {
                for (int row = 0; row < ROWS; row++) {
                    matrix[row][0] = 0;
                }
            }
    
            if (rowZero) {
                for (int col = 0; col < COLS; col++) {
                    matrix[0][col] = 0;
                }
            }
        }
    };