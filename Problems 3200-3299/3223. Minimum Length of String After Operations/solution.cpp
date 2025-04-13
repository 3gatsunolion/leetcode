class Solution {
public:
    int minimumLength(string s) {
        vector<int> freq(26, 0);
        for (char c : s) {
            freq[c-'a']++;
        }

        int res = 0;
        for (int count : freq) {
            if (count == 0) continue;
            if (count & 1) {
                res++;
            } else {
                res += 2;
            }
        }
        return res;
    }
};