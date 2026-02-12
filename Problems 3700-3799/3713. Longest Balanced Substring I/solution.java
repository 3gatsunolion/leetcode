class Solution {
    public int longestBalanced(String s) {
        // Note: s only consists of lowercase English letters
        // so to make it easier, convert to array of nums
        int n = s.length();
        int[] chars = new int[n];
        for (int i = 0; i < n; i++) {
            chars[i] = s.charAt(i) - 'a';
        }

        int res = 0;
        for (int l = 0; l < n; l++) {
            if (n - l <= res) {
                break;
            }

            int count[] = new int[26];
            int unique = 0;
            int maxFreq = 0;
            for (int r = l; r < n; r++) {
                if (count[chars[r]] == 0) {
                    unique++;
                }

                count[chars[r]]++;

                if (count[chars[r]] > maxFreq) {
                    maxFreq = count[chars[r]];
                }

                int len = r - l + 1;
                if (unique * maxFreq == len && len > res) {
                    res = len;
                } 
            }
        }

        return res;
    }
}