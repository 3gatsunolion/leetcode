class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        vector<double> currRow(1, poured);

        for (int row = 0; row <= query_row; row++) {
            vector<double> nextRow(row+2, 0);
            for (int i = 0; i <= row; i++) {
                if (currRow[i] >= 1) {
                    nextRow[i] += (currRow[i]-1)/2;
                    nextRow[i+1] += (currRow[i]-1)/2;
                    currRow[i] = 1;
                }
            }
            if (row != query_row) swap(currRow, nextRow);
        }

        return currRow[query_glass];
    }
};