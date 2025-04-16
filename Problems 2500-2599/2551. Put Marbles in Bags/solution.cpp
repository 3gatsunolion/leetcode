class Solution {
public:
    long long putMarbles(vector<int>& weights, int k) {
        // [w0, wi], [wi+1, wi2], [wi2+1, wi3],...[wk, wn-1]
        // score = w0 + [wi + wi+1] + ... + wn-1
        // for max: find max k-1 pairs and add end 2 nums
        // for min: find min k-1 pairs and add end 2 nums
        int n = weights.size();

        if (k == 1 || n == k) return 0;

        vector<int> candidates;
        for (int i = 0; i < n - 1; i++) {
            candidates.push_back(weights[i] + weights[i+1]);
        }

        sort(candidates.begin(), candidates.end());
        // technically don't need to count these two
        // vals because they're the same and we're subtracting
        // both
        // long long minScore = weights[0] + weights[n-1];
        // long long maxScore = weights[0] + weights[n-1];
        long long minScore = 0;
        long long maxScore = 0;

        for (int i = 0; i < k-1; i++) {
            minScore += candidates[i];
            maxScore += candidates[n-i-2];
        }
        return maxScore - minScore;
    }
};