class Solution {
    public int totalMoney(int n) {
        // 1 + 2 + ... + 7 = 28
        // 2 + 3 + ... + 8
        // 3 + 4 + ... + 9
        // -> 7 * (1 + 2)
        int numFullWeeks = n / 7;
        int rem = n % 7;
        int total = 28 * numFullWeeks + numFullWeeks * (numFullWeeks - 1) / 2 * 7;
        // Add remaining non-full week days
        total += numFullWeeks * rem + (rem + 1) * rem / 2;
        return total;
    }
}