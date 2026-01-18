class Solution {
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>>& box) {
        int m = box.size(), n = box[0].size();
        vector<vector<char>> rotated(n, vector<char>(m, '.'));

        for (int i = 0; i < m; i++) {
            int nextStoneRow = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '#') {
                    rotated[nextStoneRow--][m-i-1] = '#';
                // Reset nextStonePos when we see an obstacle
                // any stones we see must stack on top of this
                } else if (box[i][j] == '*') {
                    rotated[j][m-i-1] = '*';
                    nextStoneRow = j-1;
                }
            }
        }
        return rotated;
    }
};