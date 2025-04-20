class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        vector<string> res;
        getTransformations(0, s, "", res);
        return res;
    }

    void getTransformations(int i, string& s, string curr, vector<string>& res) {
        if (i == s.length()) {
            res.emplace_back(curr);
            return;
        }

        if (isalpha(s[i])) {
            getTransformations(i+1, s, curr + (char)tolower(s[i]), res);
            getTransformations(i+1, s, curr + (char)toupper(s[i]), res);
        } else {
            getTransformations(i+1, s, curr+s[i], res);
        }
    }
};