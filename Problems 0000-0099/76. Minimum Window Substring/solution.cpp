class Solution {
    public:
        string minWindow(string s, string t) {
            unordered_map<char, int> freq;
            int numCharsLeft = t.length();
            for (char c : t) {
                freq[c]++;
            }
    
            int n = s.length();
            int minLen = n + 1;
            int left = 0;
            int start = 0;
            for (int right = 0; right < n; right++) {
                if (freq[s[right]] > 0) numCharsLeft--;
                if (freq.find(s[right]) != freq.end()) {
                    freq[s[right]]--;
                }
    
                while (numCharsLeft == 0) {
                    int currLen = right - left + 1;
                    if (currLen < minLen) {
                        minLen = currLen;
                        start = left;
                    }
    
                    if (freq.find(s[left]) != freq.end()) {
                        if (freq[s[left]] == 0) numCharsLeft++;
                        freq[s[left]]++;
                    }
    
                    left++;
                }
    
            }
    
            if (minLen == n + 1) return "";
            return s.substr(start, minLen);
        }
    };