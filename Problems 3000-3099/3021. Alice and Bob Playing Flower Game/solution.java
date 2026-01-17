class Solution {
    public long flowerGame(int n, int m) {
        // Goal: Be the person to pick the last flower across
        // both lanes
        // -> Alice wins when x + y is odd (odd + even = odd)

        // If n is odd -> (n + 1) / 2 * (m) / 2 
        // If m is odd -> (n / 2) * (m + 1) / 2
        // Add it together:
        // (n + n % 2) / 2 * (m) / 2 + (n / 2) * (m / 2 + m % 2)
        // 1. If m and n are even -> (m * n) / 2
        // 2. One of m and n is odd
        // -> n = 2a + 1, m = 2b
        // -> We want: (2a + 2) / 2 * (2b) / 2 + (2a) / 2 * (2b) / 2
        // -> (a + 1) * b + ab = ab + b + ab = 2ab + b
        // Result: ((2a + 1) * 2b) / 2 = (4ab + 2b) / 2 = 2ab + b
        // 3. If m and n are both odd ->
        // We want: (2a + 2) / 2 * 2b / 2 + 2a/2 * (2b + 2) / 2
        // -> (a + 1) * b + a * (b + 1) = ab + b + ab + a = 2ab + a + b
        // Result: (2a + 1)*(2b + 1) / 2 = (4ab + 2a + 2b + 1) / 2
        // -> 2ab + a + b + 0.5 -> rounded down to 2ab + a + b
        return ((long) m * n) / 2;
    }
}