class Solution {
public:
    long long countGoodIntegers(int n, int k) {
        vector<int> fact(n+1, 0);
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = i*fact[i-1];
        }

        int half = (n + 1) / 2;
        long long res = 0;
        unordered_map<string, bool> seen;
        // Iterate over all palindrome halves
        int start = pow(10, half-1);
        int end = pow(10, half);
        for (int num = start; num < end; num++) {
            string paliStr = to_string(num);
            string secondHalf = "";
            int paliSize = paliStr.size();
            for (int i = paliSize - 1 - (n % 2); i >= 0; i--) {
                paliStr += paliStr[i];
            }

            string key = paliStr;
            sort(key.begin(), key.end());

            vector<int> freq(10, 0);
            // this combination of digits hasn't been seen before
            // since we can rearrange the digits
            if (seen.find(key) == seen.end()) {
                if (stol(paliStr) % k != 0) continue;
                // figure out different palindrome combinations
                // you can make out of these digits
                for (int i = 0; i < n; i++) {
                    freq[paliStr[i]-'0']++;
                }

                // Can place all non-zero items in first position
                // then remaining spaces (n-1)*(n-2)*...*1
                int perm = (n-freq[0])*fact[n-1];

                // Remove duplicates
                for (int i = 0; i <= 9; i++) {
                    perm /= fact[freq[i]];
                }
                res += perm;
                seen[key] = true;
            }
        }
        return res;
    }
};