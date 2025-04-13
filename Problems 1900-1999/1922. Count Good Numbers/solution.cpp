class Solution {
public:
    const int mod = 1e9+7;
    long long powerMod(long long a, long long b) {
        if(b == 0)
            return 1;
        long long x = powerMod(a, b/2);
        if(b % 2 == 0)
            return (x * x) % mod;
        else
            return (((a * x) % mod) * x) % mod;
    }
    
    int countGoodNumbers(long long n) {
        long long numPrime = n/2;
        long long numEven = n - numPrime;
        return (powerMod(5, numEven) * powerMod(4, numPrime)) % mod;
    }
};