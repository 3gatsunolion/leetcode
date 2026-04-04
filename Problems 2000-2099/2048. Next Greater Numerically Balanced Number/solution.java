class Solution {
    public int nextBeautifulNumber(int n) {
        if (n == 0) return 1;
        // Given constraints: 0 <= n <= 10**6 -> only numbers
        // from 1 to 6 are needed
        int[] count = new int[7];
        // int numLength = String.valueOf(n).length();
        int numLength = (int) Math.log10(n) + 1;
        return findNext(n, numLength + 1, 0, 0, count);
    }

    private int findNext(int num, int maxLen, int curr, int currLen, int[] count) {
        if (curr > num && isBalanced(count)) {
            return curr;
        }

        if (currLen >= maxLen) {
            return 0; // Since curr <= n here
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < 7; i++) {
            int cand = curr * 10 + i;
            // System.out.println(cand);
            // We've got a better valid candidate already
            if (res < cand) break;
            // Cannot use this number (used up or can't fit)
            if (count[i] == i || i - count[i] > maxLen - currLen) continue;
            count[i]++;
            int n = findNext(num, maxLen, cand, currLen + 1, count);
            if (n > 0 && n < res) {
                res = n;
            } 
            count[i]--;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private boolean isBalanced(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0 && count[i] != i) {
                return false;
            }
        }
        return true;
    }
}