class Solution {
public:
    bool isPossibleToRearrange(string s, string t, int k) {
        int subLen = t.length() / k;
        unordered_map<string, int> mp;
        for (int i = 0; i < t.length(); i += subLen) {
            mp[s.substr(i, subLen)]++;
            mp[t.substr(i, subLen)]--;
        }
        
        for (auto& [key, count] : mp) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
};