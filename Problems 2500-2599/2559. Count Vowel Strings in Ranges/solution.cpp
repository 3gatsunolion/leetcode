class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        unordered_set<char> vowels = { 'a', 'e', 'i', 'o', 'u' };
        int n = words.size();
        vector<int> prefixSums(n + 1, 0);

        for (int i = 0; i < n; i++) {
            prefixSums[i+1] = prefixSums[i];
            int wordSize = words[i].length();
            if (vowels.find(words[i][0]) != vowels.end() && vowels.find(words[i][wordSize-1]) != vowels.end()) {
                prefixSums[i+1]++;
            }
        }

        int numQueries = queries.size();
        vector<int> ans(numQueries);
        for (int i = 0; i < numQueries; i++) {
            ans[i] = prefixSums[queries[i][1]+1] - prefixSums[queries[i][0]];
        }

        return ans;
    }
};