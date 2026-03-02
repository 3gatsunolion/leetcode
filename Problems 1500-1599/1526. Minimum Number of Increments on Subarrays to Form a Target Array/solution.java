class Solution {
    public int minNumberOperations(int[] target) {
        // Worst case: sum of all numbers in target
        // (take subarray of 1 each time)
        // Best case: Minimum value in target

        // One operation corresponds to laying out
        // a continuous row of bricks if we visualize
        // target as heights of columns of bricks

        int n = target.length;
        if (n == 0) return 0;

        int minOp = target[0];
        for (int i = 1; i < n; i++) {
            minOp += Math.max(target[i] - target[i - 1], 0);
        }

        return minOp;
    }
}