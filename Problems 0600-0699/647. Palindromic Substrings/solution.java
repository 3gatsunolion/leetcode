class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countPalindromes(s, i, i);
            count += countPalindromes(s, i, i + 1);
        }
        return count;
    }

    private int countPalindromes(String s, int c1, int c2) {
        int count = 0;

        while (c1 >= 0 && c2 < s.length() && s.charAt(c1) == s.charAt(c2)) {
            c1--;
            c2++;
            count++;
        }

        return count;
    }
}