class Solution {
    public long minOperations(int[][] queries) {
        // [4^0, 1, 2, 3] -> 1 operation to become 0
        // [4^1, ..., 15] -> 2 operations to become 0
        // [4^2, ..., 63] -> 3 operations to become 0
        long res = 0;
        for (int[] query : queries) {
            long l = query[0], r = query[1];
            long sum = 0;
            // long ops = (long) (Math.log(l) / Math.log(4));
            long ops = 0, group = 1;
            while (group * 4 <= l) {
                group *= 4;
                ops++;
            }
            for (; group <= r; group *= 4) {
                long ql = Math.max(group, l), qr = Math.min(group * 4 - 1, r);
                sum += ++ops * (qr - ql + 1);
            }
            res += (sum + 1) / 2;
        }
        return res;
    }
}