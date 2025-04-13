class Solution {
public:
    string removeOccurrences(string s, string part) {
        int i = 0;
        int partLen = part.length();
        string res = s;
        for (char c : s) {
            res[i++] = c;
            if (i >= partLen && res.substr(i-partLen, partLen) == part) {
                i -= partLen;
            }
        }
        return res.substr(0, i);
    }
};