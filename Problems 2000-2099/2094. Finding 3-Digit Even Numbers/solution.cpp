class Solution {
    public:
        vector<int> findEvenNumbers(vector<int>& digits) {
            vector<int> freq(10, 0);
            for (int digit: digits) {
                freq[digit]++;
            }
    
            vector<int> res;
            for (int cand = 100; cand < 999; cand += 2) {
                vector<int> freqCand(10, 0);
    
                int num = cand;
                while (num > 0) {
                    freqCand[num % 10]++;
                    num /= 10;
                }
    
                bool possible = true;
                for (int i = 0; i < 10; i++) {
                    if (freq[i] < freqCand[i]) {
                        possible = false;
                        break;
                    }
                }
    
                if (possible) {
                    res.push_back(cand);
                }
            }
    
            return res;
        }
    };