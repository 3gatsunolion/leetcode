class Solution {
    public int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];

        int n = s.length();

        for (int i = 0; i < 26; i++) {
            first[i] = -1;
            last[i] = n;
        }

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] > -1 && last[i] < n) {
                res += count(first[i], last[i], s);
            }
        }

        return res;
    }

    private int count(int start, int end, String s) {
        if (start >= end) return 0;
        int[] count = new int[26];

        for (int i = start + 1; i <= end - 1; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int res = 0;
        for (int freq : count) {
            if (freq > 0) res++;
        }
        return res;
    }
}