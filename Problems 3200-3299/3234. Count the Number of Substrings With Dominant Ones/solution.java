class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();

        if (n == 0) return 0;

        int[] closestZeroToLeft = new int[n];
        closestZeroToLeft[0] = -1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == '0') {
                closestZeroToLeft[i] = i - 1;
            } else {
                closestZeroToLeft[i] = closestZeroToLeft[i - 1];
            }
        }

        int res = 0;
        for (int right = 0; right < n; right++) {
            int left = right;
            int numZero = s.charAt(right) == '0' ? 1 : 0;

            while (left >= 0 && numZero * numZero <= n) {
                // Extend to just before next zero
                int numOnes = right - closestZeroToLeft[left] - numZero;
                
                if (numZero * numZero <= numOnes) {
                    // Best case scenario: All substrings where
                    // we extend left from left to closestZeroToLeft[left] + 1 is valid
                    // But there may be some cases where at
                    // a certain point as left goes more to
                    // the right, that the
                    // numZero * numZero <= numOnes condition
                    // is no longer satisfied
                    // in other words, at some point k, this
                    // condition is violated:
                    // (numOnes - k) >= numZero * numZero
                    // -> k <= numOnes - numZero * numZero
                    // Thus, maximum valid steps is:
                    // k = numOnes - numZero * numZero + 1
                    res += Math.min(left - closestZeroToLeft[left], numOnes - numZero * numZero + 1);
                }

                left = closestZeroToLeft[left];
                numZero++;
            }
        }

        return res;
    }
}