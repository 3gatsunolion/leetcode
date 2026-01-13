class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int end = n - 1; end > 1; end--) {
            for (int i = 0; i < end; i++) {
                chars[i] = (char) ((chars[i] - '0' + chars[i + 1] - '0') % 10 + '0');
            }
        }
        return chars[0] == chars[1];
    }
}