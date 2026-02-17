class Solution {
    public:
        int findKthNumber(int n, int k) {
            int curr = 1;
            k--;
            while (k > 0) {
                int count = countNodes(curr, n);
                if (count <= k) {
                    k -= count;
                    curr++;
                } else {
                    curr *= 10;
                    k--;
                }
            }
            return curr;
        }
    
        int countNodes(long start, int n) {
            long end = start + 1;
            int count = 0;
            while (start <= n) {
                count += min((long)n + 1, end) - start;
                start *= 10;
                end *= 10;
            }
            return count;
        }
    };