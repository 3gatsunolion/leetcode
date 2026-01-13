class Solution {
    public:
        int maxDifference(string s, int k) {
            int n = s.size();
    
            auto getState = [](int a, int b) {
                return ((a & 1) << 1) | (b & 1);
            };
            
            int res = INT_MIN;
            for (char a = '0'; a <= '4'; a++) {
                for (char b = '0'; b <= '4'; b++) {
                    if (a == b) continue;
    
                    int rightA = 0;
                    int rightB = 0;
    
                    int leftA = 0;
                    int leftB = 0;
    
                    int left = -1;
    
                    int minLDiff[4] = {INT_MAX, INT_MAX, INT_MAX, INT_MAX};
    
                    for (int right = 0; right < n; right++) {
                        rightA += s[right] == a ? 1 : 0;
                        rightB += s[right] == b ? 1 : 0;
    
                        int bDiff = rightB - leftB;
                        while (right - left >= k && bDiff >= 2) {
                            int leftState = getState(leftA, leftB);
    
                            minLDiff[leftState] = min(minLDiff[leftState], leftA - leftB);
    
                            left++;
                            leftA += s[left] == a ? 1 : 0;
                            leftB += s[left] == b ? 1 : 0;
                        }
    
                        int need = getState(rightA, rightB) ^ 0b10;
                        if (minLDiff[need] != INT_MAX) {
                            res = max(res, rightA - rightB - minLDiff[need]);
                        }
                    }
                }
            }
    
            return res;
        }
    };