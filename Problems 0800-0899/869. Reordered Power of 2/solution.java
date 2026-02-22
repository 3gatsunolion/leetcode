class Solution {
    public boolean reorderedPowerOf2(int n) {
        // char[] chars = String.valueOf(n).toCharArray();
        // Arrays.sort(chars);
        // String nSorted = new String(chars);

        // // Test all possible powers of 2s (32 bit)
        // for (int i = 0; i < 32; i++) {
        //     chars = String.valueOf(1 << i).toCharArray();
        //     Arrays.sort(chars);
        //     String iSorted = new String(chars);
        //     if (nSorted.equals(iSorted)) {
        //         return true;
        //     }
        // }
        // return false;

        // This only works if len(n) < 10
        int c = countDigits(n);
        for (int i = 0; i < 32; i++) {
            if (countDigits(1 << i) == c) {
                return true;
            }
        }
        return false;
    }

    public int countDigits(int n) {
        int res = 0;
        for (; n > 0; n /= 10) {
            res += Math.pow(10, n % 10);
        }
        return res;
    }
}