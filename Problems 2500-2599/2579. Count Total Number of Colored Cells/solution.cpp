class Solution {
public:
    long long coloredCells(int n) {
        return n*(n-1l)*2+1l;
    }
};