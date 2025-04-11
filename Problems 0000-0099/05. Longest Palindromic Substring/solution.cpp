class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.length();
        string longest = "";
        for (int i = 0; i < n; i++) {
            pair<int, int> one = expandFromCenter(s, i, i);
            pair<int, int> two = expandFromCenter(s, i, i+1);
            int l1 = one.second - one.first + 1;
            if (l1 > longest.length()) {
                longest = s.substr(one.first, l1);
            }
            int l2 = two.second - two.first + 1;
            if (l2 > longest.length()) {
                longest = s.substr(two.first, l2);
            }
        }
        return longest;
    }

    pair<int, int> expandFromCenter(const string& s, int c1, int c2) {
        int n = s.length();
        while (c1 >= 0 && c2 < n && s[c1] == s[c2]) {
            c1--;
            c2++;
        }
        return {c1+1, c2-1};
    }
};