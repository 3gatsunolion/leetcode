class Solution {
    private final int MOD = (int) Math.pow(10, 9) + 7;
    public int countPermutations(int[] complexity) {
        // If there are computers other than root where their
        // complexity is <= root, then it's impossible
        // Once this condition passes, then there's (n-1)! permutations
        int n = complexity.length;
        long res = 1;
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) return 0;
            res = (res * i) % MOD;
        }

        return (int) res;
    }
}