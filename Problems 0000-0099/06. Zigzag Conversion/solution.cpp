class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        vector<string> rows(numRows, "");
        int currRow = 0;
        int step = 1;
        for (int i = 0; i < s.length(); i++) {
            rows[currRow] += s[i];
            if (currRow == 0) {
                step = 1;
            } else if (currRow == numRows-1) {
                step = -1;
            }
            currRow += step;
        }

        string res = "";
        for (const string& row : rows) {
            res += row;
        }
        return res;
    }
};