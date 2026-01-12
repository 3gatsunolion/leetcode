class Solution {
    public:
        int maxProfit(vector<int>& prices) {
            int n = prices.size();
            if (n == 0) return 0;
    
            int minPrice = prices[0];
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                int currProfit = prices[i]-minPrice;
                if (currProfit > maxProfit) {
                    maxProfit = currProfit;
                }
    
                if (prices[i] < minPrice) {
                    minPrice = prices[i];
                }
            }
    
            return maxProfit;
        }
    };