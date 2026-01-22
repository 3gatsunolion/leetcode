class Solution {
    public int maxFreqSum(String s) {
        int[] vowelFreq = new int[26];
        int[] consonantFreq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                vowelFreq[c - 'a']++;
            } else {
                consonantFreq[c - 'a']++;
            }
        }

        int vowelMax = 0;
        int consonantMax = 0;
        for (int i = 0; i < vowelFreq.length; i++) {
            if (vowelFreq[i] > vowelMax) {
                vowelMax = vowelFreq[i];
            }
            if (consonantFreq[i] > consonantMax) {
                consonantMax = consonantFreq[i];
            }
        }
        return vowelMax + consonantMax;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}