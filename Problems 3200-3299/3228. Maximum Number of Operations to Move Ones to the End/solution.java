class Solution {
    public int maxOperations(String s) {
        // Basically count how many groups of zeroes
        // to the right of ones

        // int n = s.length();
        // int numGroupsOfZero = 0;
        // int res = 0;
        // for (int i = n - 1; i >= 0; i--) {
        //     if (s.charAt(i) == '0' && (i == n - 1 || s.charAt(i + 1) != '0')) {
        //         numGroupsOfZero++;
        //     } else if (s.charAt(i) == '1') {
        //         res += numGroupsOfZero;
        //     }
        // }

        // return res;

        int n = s.length();
        int numGroupsOfZero = 0;
        int res = 0;
        int i = n - 1;
        while (i >= 0) {
            while (s.charAt(i) == '1') {
                i--;
                res += numGroupsOfZero;
                if (i < 0) return res;
            }

            while (s.charAt(i) == '0') {
                i--;
                if (i < 0) return res;
            }

            numGroupsOfZero++;
        }
        
        return res;
    }
}