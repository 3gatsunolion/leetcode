class Solution {
    // public int minFlips(String s) {
    //     // Sliding Window approach
    //     int n = s.length();
    //     int res = Integer.MAX_VALUE;
        
    //     int flipsNeeded = 0;
    //     // Iterate over doubled length to simulate all circular rotations
    //     for(int i = 0; i < 2 * n; i++) {
    //         int windowIndex = i % n;
            
    //         if ((s.charAt(windowIndex) - '0') != (i % 2)) {
    //             flipsNeeded++;
    //         }
            
    //         // Shrink window
    //         if (i >= n) {
    //             int bitToRemove = s.charAt(windowIndex) - '0';
    //             if (bitToRemove != (windowIndex % 2)) {
    //                 flipsNeeded--;
    //             }
    //         }
            
    //         // flipsNeeded -> flips needed to get s to alternate bits where
    //         // even indices are 0 and odd indices are 1
    //         // n - flipsNeeded -> opposite of above
    //         if (i >= n - 1) {
    //             res = Math.min(res, Math.min(flipsNeeded, n - flipsNeeded));
    //         }
    //     }
        
    //     return res;
    // }

    // public int minFlips(String s) {
    //     int n = s.length();

    //     // 1. If n is even, "rotating" the string has no effect on
    //     // the final minimum flips needed. This is because any
    //     // alternating even length string starts and end with different
    //     // characters, so any prefix substring we break off and append
    //     // to the end will still result in an alternating string too,
    //     // since the first and last character are different
    //     // So in this case, we don't have to account for type 1 operations
    //     int flipsNeeded = 0;
    //     for (int i = 0; i < n; i++) {
    //         if ((s.charAt(i) - '0') != (i % 2)) {
    //             flipsNeeded++;
    //         }
    //     }

    //     int res = Math.min(flipsNeeded, n - flipsNeeded);
        
    //     // If n is odd, an alternating string must be of the form:
    //     // 010...10 or 101..01
    //     // s can either already be alternating, or, after flipping minimum
    //     // bits, be of the form:
    //     // ...10|010... or ...01|101...
    //     // So one point where there's 2 consecutive 0 or 1, and the rest
    //     // being alternating
    //     if (n % 2 == 1) {
    //         // Min flips to get alternating string starting with 0
    //         int[] suffix0 = new int[n];
    //         // Min flips to get alternating string starting with 1
    //         int[] suffix1 = new int[n];
    //         suffix0[n - 1] = needsFlip(s, n - 1, 0);
    //         suffix1[n - 1] = needsFlip(s, n - 1, 1);
    //         for (int i = n - 2; i >= 0; i--) {
    //             suffix0[i] = needsFlip(s, i, 0) + suffix1[i + 1];
    //             suffix1[i] = needsFlip(s, i, 1) + suffix0[i + 1];
    //         }

    //         // Min flips to get alternating string ending in 0
    //         int[] prefix0 = new int[n + 1];
    //         // Min flips to get alternating string ending in 1
    //         int[] prefix1 = new int[n + 1];
    //         for (int i = 0; i < n - 1; i++) {
    //             prefix0[i + 1] = needsFlip(s, i, 0) + prefix1[i];
    //             prefix1[i + 1] = needsFlip(s, i, 1) + prefix0[i];

    //             res = Math.min(res, Math.min(
    //                            prefix0[i + 1] + suffix0[i + 1],
    //                            prefix1[i + 1] + suffix1[i + 1]
    //                            )
    //                         );
    //         }
    //     }
        
    //     return res;
    // }

    // private int needsFlip(String s, int i, int want) {
    //     return (s.charAt(i) - '0') == want ? 0 : 1;
    // }

    public int minFlips(String s) {
        int n = s.length();

        // 1. If n is even, "rotating" the string has no effect on
        // the final minimum flips needed. (See second solution for explanation)
        int flipsNeeded = 0;
        for (int i = 0; i < n; i++) {
            if ((s.charAt(i) - '0') != (i % 2)) {
                flipsNeeded++;
            }
        }

        int res = Math.min(flipsNeeded, n - flipsNeeded);
        
        if (n % 2 == 1) {
            // Right now, flipsNeeded tells us the min flips needed
            // to transform s to alternating 0101010...10 string
            // Now we need to account for rotations -> meaning all appending
            // bits needed to be flipped, so we check if we flipped the bit
            // in previous calculation, then we undo the flip
            int expectedBit = 0;
            for (int r = 0; r < n - 1; r++) {
                int bit = s.charAt(r) - '0';
                boolean wasPreviouslyFlipped = expectedBit != bit;
                if (wasPreviouslyFlipped) {
                    flipsNeeded--;
                } else {
                    flipsNeeded++;
                }
                expectedBit ^= 1;
                res = Math.min(res, Math.min(flipsNeeded, n - flipsNeeded));
            }
        }
        
        return res;
    }
}