class Solution {
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int hold2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int price : prices) {
            hold1 = Math.max(hold1, -price);
            sell1 = Math.max(sell1, price + hold1);
            hold2 = Math.max(hold2, sell1 - price);
            sell2 = Math.max(sell2, price + hold2);
        }
        return sell2;
    }
}