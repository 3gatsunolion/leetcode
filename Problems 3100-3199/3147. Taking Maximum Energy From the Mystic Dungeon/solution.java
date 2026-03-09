class Solution {
    public int maximumEnergy(int[] energy, int k) {
        // int n = energy.length;
        // // gain[i] = how much energy you absorb it you start at
        // // ith wizard
        // int[] gain = new int[n];
        // int res = Integer.MIN_VALUE;
        // for (int i = n - 1; i >= 0; i--) {
        //     gain[i] = energy[i];
        //     if (i + k < n) {
        //         gain[i] += gain[i + k];
        //     }

        //     if (res < gain[i]) {
        //         res = gain[i];
        //     }
        // }
        // return res;

        // int res = Integer.MIN_VALUE;
        // for (int start = 0; start < k; start++) {
        //     int gain = 0;
        //     for (int i = start; i < energy.length; i += k) {
        //         gain = Math.max(gain + energy[i], energy[i]);
        //     }
        //     if (gain > res) {
        //         res = gain;
        //     }
        // }
        // return res;

        int res = Integer.MIN_VALUE;
        int n = energy.length;
        for (int end = n - k; end < n; end++) {
            int gain = 0;
            for (int start = end; start >= 0; start -= k) {
                gain += energy[start];
                if (gain > res) {
                    res = gain;
                }
            }
        }
        return res;
    }
}