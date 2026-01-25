class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        if (m < n) return -1;

        int[] lpsTable = getLookupTable(needle);

        int needleIdx = 0;
        for (int i = 0; i < m; i++) {
            // Adjust needleIdx on mismatch
            while (needleIdx > 0 && haystack.charAt(i) != needle.charAt(needleIdx)) {
                needleIdx = lpsTable[needleIdx - 1];
            }

            if (haystack.charAt(i) == needle.charAt(needleIdx)) {
                needleIdx++;
            }

            if (needleIdx == n) {
                return i - n + 1; // Found the match
            }
        }

        return -1;
    }

    // KMP: The algorithm operates in O(n + m) time, where n is the text length and m is the pattern length.
    private int[] getLookupTable(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        // table[i] = if you're comparing string with pattern s and
        // there's a mismatch at index i of pattern s, then table[i-1]
        // tells us which index of pattern s we can start comparing from
        int[] table = new int[n];

        // To achieve this table, we need to keep track of
        // LPS (Longest Proper Prefix which is also a Suffix)
        // Visualize: aabbzyxwaabb -> lps is 4
        // Let's say we are comparing this string with another string, and
        // it mismatches at the final letter, then table[-2] should give us
        // 3 because we can see the last 3 characters are the same as the
        // first 3 characters so we can begin matching again at index 3

        int currLPSLen = 0;
        for (int i = 1; i < n; i++) {
            while (currLPSLen > 0 && chars[i] != chars[currLPSLen]) {
                // Shrink currLPSLen -> Remember: table[i-1] tells us
                // where to keep matching if there's a mismatch at index i
                currLPSLen = table[currLPSLen - 1];
            }

            if (chars[i] == chars[currLPSLen]) {
                currLPSLen++;
            }

            table[i] = currLPSLen;
        }

        return table;
    }
}