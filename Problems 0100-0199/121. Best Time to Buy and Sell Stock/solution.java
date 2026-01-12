class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int minSoFar = prices[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < minSoFar) {
                minSoFar = prices[i];
            } else {
                res = Math.max(res, prices[i] - minSoFar);
            }
        }
        return res;
    }
}