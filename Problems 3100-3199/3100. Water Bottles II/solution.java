class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        // Let b = numBottles
        // e = numExchange
        // x = number of exchanges possible (extra bottles earned)
        // We need:
        // b + x >= (e + i)(summation for i = 0...(x-1))
        // b + x >= x*e + x*(x-1)/2
        // x^2 - x + 2xe - 2b - 2x <= 0
        // x^2 -3x + 2xe - 2b <= 0
        // x^2 + (2e - 3)x - 2b <= 0
        // we want to maximize x -> take the discriminant:
        // x = -(2e - 3) +- sqrt((2e - 3)^2 - 4(-2b)) / 2
        // NOTE: This method implies the ability to "borrow" an empty bottle from our "future selves". To correct for this in integer terms, we simply replace b with b−1 in our calculations!
        int d = (int) Math.pow(2*numExchange - 3, 2) + 8 * (numBottles - 1);
        return numBottles + (int) ((-2*numExchange + 3) + Math.sqrt(d)) / 2;
        
        // int res = numBottles;
        // while (numBottles >= numExchange) {
        //     numBottles -= numExchange - 1;
        //     numExchange++;
        //     res++;
        // }
        // return res;
    }
}