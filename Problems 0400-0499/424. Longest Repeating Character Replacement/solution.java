class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int currMaxFreq = 0;
        int left = 0;
        int res = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            
            if (currMaxFreq < freq[c - 'A']) {
                currMaxFreq = freq[c - 'A'];
            }

            if (right - left + 1 - currMaxFreq > k) {
                freq[s.charAt(left++) - 'A']--;
            }

            res = right - left + 1;
        }
        return res;
    }
}