class Solution {
    private final int MOD = (int) Math.pow(10, 9) + 7;
    public int numSub(String s) {
        char[] sChar = s.toCharArray();

        int currLen = 0;
        int res = 0;
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] == '1') {
                currLen++;
                res = (res + currLen) % MOD;
            } else {
                currLen = 0;
            }
        }
        return res;
    }
}