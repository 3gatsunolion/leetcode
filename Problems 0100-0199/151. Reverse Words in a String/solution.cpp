class Solution {
    public:
        string reverseWords(string s) {
            reverse(s.begin(), s.end());
            int i = 0;
            int j = 0;
            int n = s.length();
            while (j < n) {
                while (j < n && s[j] == ' ') j++;
    
                if (j == n) break;
    
                if (i > 0) s[i++] = ' ';
    
                int l = j;
                int left = i;
                while (j < n && s[j] != ' ') {
                    s[i++] = s[j++];
                }
    
                int wordLen = j-l;
                reverse(s.begin() + left, s.begin() + left + wordLen);
            }
    
            s.resize(i);
            return s;
        }
    };