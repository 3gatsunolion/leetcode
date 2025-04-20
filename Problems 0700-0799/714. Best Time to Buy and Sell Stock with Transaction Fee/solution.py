class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        n = len(prices)
        # buy[i]: Max profit after buying a stock between day 1...i
        # buy = [float("-inf")] * n
        # buy[0] = -prices[0]
        buy = -prices[0]
        # sell[i]: Max profit after selling a stock (no holding
        # stocks at this point)
        # sell = [0] * n
        sell = 0

        for i in range(1, n):
            price = prices[i]
            buy = max(buy, sell - price)
            sell = max(sell, buy + price - fee)
        
        return sell