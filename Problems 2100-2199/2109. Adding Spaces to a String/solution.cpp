class Solution {
public:
    string addSpaces(string s, vector<int>& spaces) {
        string res(s.length() + spaces.size(), ' ');
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (j < spaces.size() && spaces[j] == i) {
                j++;
            }
            res[i+j] = s[i];
        }
        return res;
    }
};