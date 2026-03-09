class Solution {
    public:
        string clearStars(string s) {
            vector<vector<int>> pos(26);
            priority_queue<char, vector<char>, greater<char>> minHeap;
            int n = s.length();
            vector<bool> keep(n, true);
            for (int i = 0; i < n; i++) {
                if (s[i] == '*') {
                    char delChar = minHeap.top();
                    int removeIndex = pos[delChar-'a'].back();
                    pos[delChar-'a'].pop_back();
                    keep[i] = false;
                    keep[removeIndex] = false;
    
                    if (pos[delChar-'a'].size() == 0) {
                        minHeap.pop();
                    }
                } else {
                    if (pos[s[i]-'a'].size() == 0) {
                        minHeap.push(s[i]);
                    }
                    pos[s[i]-'a'].push_back(i);
                }
            }
    
            string res = "";
            for (int i = 0; i < n; i++) {
                if (keep[i]) {
                    res += s[i];
                }
            }
    
            return res;
        }
    };