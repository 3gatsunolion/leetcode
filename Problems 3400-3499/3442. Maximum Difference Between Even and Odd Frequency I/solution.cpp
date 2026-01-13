class Solution {
    public:
        int maxDifference(string s) {
            vector<int> freq(26, 0);
    
            for (char c : s) {
                freq[c-'a']++;
            }
    
            int maxOdd = 0;
            int minEven = s.length() + 1;
            for (int count : freq) {
                if (count == 0) continue;
                if ((count % 2) == 0) {
                    minEven = min(minEven, count);
                } else {
                    maxOdd = max(maxOdd, count);
                }
            }
    
            return maxOdd - minEven;
        }
    };