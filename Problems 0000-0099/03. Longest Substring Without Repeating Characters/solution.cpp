class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        // Most recent index of char
        unordered_map<char, int> charMap;
        int l = 0;
        int res = 0;
        for (int r = 0; r < s.size(); r++) {

            if (charMap.count(s[r]) == 0 || charMap[s[r]] < l) {
                charMap[s[r]] = r;
                res = max(res, r - l + 1);
            } else {
                l = charMap[s[r]] + 1;
                charMap[s[r]] = r;
            }
        }
        return res;
    }
};