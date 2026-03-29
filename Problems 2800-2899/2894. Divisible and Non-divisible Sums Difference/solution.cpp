class Solution {
    public:
        int differenceOfSums(int n, int m) {
            int k = n / m;
            int num1 = ((k+1)*k/2)*m;
            int total = (n+1)*n/2;
            return total - 2*num1;
        }
    };