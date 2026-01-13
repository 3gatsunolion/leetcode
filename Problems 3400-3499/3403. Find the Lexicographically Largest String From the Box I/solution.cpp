class Solution {
    public:
        string answerString(string word, int numFriends) {
            if (numFriends == 1) return word;
            int n = word.length();
            int m = n - numFriends + 1;
            char maxc = *max_element(word.begin(), word.end());
            string res;
            for (int i = 0; i < n; i++) {
                if (word[i] != maxc) continue;
                string cand = word.substr(i, m);
                if (cand > res) {
                    res = cand;
                }
            }
            return res;
        }
    };