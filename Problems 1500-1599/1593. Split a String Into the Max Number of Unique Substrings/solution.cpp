class Solution {
public:
    int maxUniqueSplit(string s) {
        unordered_set<string> seen;
        int maxCount = 0;
        return backtrack(s, 0, seen, maxCount);
    }

    int backtrack(string& s, int start, unordered_set<string>& seen, int& maxCount) {
        int n = s.length();
        if (start == n) {
            maxCount = max(maxCount, (int)seen.size());
            return maxCount;
        }

        if (seen.size() + n-start <= maxCount) return maxCount;

        for (int end = start; end < n; end++) {
            string split = s.substr(start, end-start+1);
            if (seen.find(split) != seen.end()) continue;
            seen.insert(split);
            backtrack(s, end+1, seen, maxCount);
            seen.erase(split);
        }
        return maxCount;
    }
};