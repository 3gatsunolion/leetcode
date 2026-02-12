class Solution {
    public int[] sumZero(int n) {
        // int[] res = new int[n];

        // for (int i = 1; i <= n / 2; i++) {
        //     res[i - 1] = i;
        //     res[n - i] = -i;
        // }
        // return res;

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i * 2 - n + 1;
        }
        return res;
    }
}