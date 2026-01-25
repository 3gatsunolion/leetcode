class Solution {
    public:
        vector<int> lexicalOrder(int n) {
            vector<int> res;
            int curr = 1;
            for (int i = 1; i <= n; i++) {
                res.push_back(curr);
                if (curr * 10 <= n) {
                    curr *= 10;
                } else {
                    // This means that if we add 1, then we're over
                    if (curr >= n) {
                        curr /= 10;
                    }
                    curr++;
                    // But if we went 109+1=110 -> we need to go back
                    while (curr % 10 == 0) {
                        curr /= 10;
                    }
                }
            }
    
            return res;
        }
    };