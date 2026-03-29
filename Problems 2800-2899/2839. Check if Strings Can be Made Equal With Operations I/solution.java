class Solution {
    public boolean canBeEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        // Even indices can only swap with even, same for odd
        return isAnagram(s1, s2, 0) && isAnagram(s1, s2, 1);
    }

    private boolean isAnagram(String s1, String s2, int start) {
        int[] freq = new int[26];

        for (int i = start; i < s1.length(); i += 2) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) return false;
        }

        return true;
    }
}