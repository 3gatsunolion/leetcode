class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = expandFromCenter(s, i, i);
            String even = expandFromCenter(s, i, i + 1);

            if (odd.length() > res.length()) {
                res = odd;
            }
            if (even.length() > res.length()) {
                res = even;
            }
        }
        return res;
    }

    public String expandFromCenter(String s, int c1, int c2) {
        while (c1 >= 0 && c2 < s.length() && s.charAt(c1) == s.charAt(c2)) {
            c1--;
            c2++;
        }
        return s.substring(c1 + 1, c2);
    }
}