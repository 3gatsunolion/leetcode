class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        if n == 0: return 0

        minPrice = prices[0]
        maxProfit = 0

        for i in range(1, n):
            if prices[i]-minPrice > maxProfit:
                maxProfit = prices[i]-minPrice
            
            minPrice = min(minPrice, prices[i])
        
        return maxProfit