class Solution {
    public:
        int minDominoRotations(vector<int>& tops, vector<int>& bottoms) {
            int n = tops.size();
            if (n == 0) return 0;
            // If solution exists, it will be either tops[0] or bottoms[0]
            vector<int> cand{ tops[0], bottoms[0] };
            for (int num : cand) {
                bool isCand = true;
                int topCount = 0;
                int bottomCount = 0;
                for (int i = 0; i < n; i++) {
                    if (tops[i] != num && bottoms[i] != num) {
                        isCand = false;
                        break;
                    }
    
                    if (tops[i] == num) topCount++;
                    if (bottoms[i] == num) bottomCount++;
                }
                if (isCand) {
                    return n - max(topCount, bottomCount);
                }
            }
            return -1;
        }
    };