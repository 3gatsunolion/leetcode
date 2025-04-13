class Solution {
public:
    int countPalindromicSubsequence(string s) {
        // palindrome of length 3:
        // 1. all same character: aaa
        // 2. one different character in middle: aba
        // --> we need to know how many
        // unique characters come after current char and
        // last occurence of current char

        vector<int> first(26, -1), last(26, -1);

        for (int i = 0; i < s.length(); i++) {
            if (first[s[i]-'a'] == -1) first[s[i]-'a'] = i;
            last[s[i]-'a'] = i;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] < last[i]) {
                res += unordered_set<char>(s.begin() + first[i] + 1, s.begin() + last[i]).size();
            }
        }
        return res;
    }
};