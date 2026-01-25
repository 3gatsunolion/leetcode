class Solution {
    public int getSum(int a, int b) {
        int sum = a;
        while (b != 0) {
            int carry = sum & b;
            // calculate places where one bit is 0 the other is 1
            // 0 ^ 1 = 1
            sum = sum ^ b;
            // Add any carrys in next iteration
            b = carry << 1;
        }
        return sum;
    }
}