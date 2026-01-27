class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        // Sliding window
        int n = prices.length;
        int mid = k / 2;
        long original = 0;
        long curr = 0;
        for (int i = 0; i < prices.length; i++) {
            original += prices[i] * strategy[i];
            if (i < k) {
                curr += (i < mid) ? 0 : prices[i];
            } else {
                curr += prices[i] * strategy[i];
            }
        }

        long res = Math.max(curr, original);
        for (int i = k; i < n; i++) {
            curr += prices[i];
            curr -= prices[i] * strategy[i];
            // i - k was 0 before, so add it back
            curr += prices[i - k] * strategy[i - k];
            // Move mid line
            curr -= prices[i - mid];
            res = Math.max(res, curr);
        }
        return res;
    }
}