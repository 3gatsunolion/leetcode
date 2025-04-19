class Solution {
public:
    string clearDigits(string s) {
        vector<char> nonDigits;

        for (char c : s) {
            if (isdigit(c)) {
                nonDigits.pop_back();
            } else {
                nonDigits.push_back(c);
            }
        }

        string res(nonDigits.begin(), nonDigits.end());
        return res;
    }
};