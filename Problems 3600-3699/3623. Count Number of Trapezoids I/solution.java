class Solution {
    private final int MOD = (int) Math.pow(10, 9) + 7;
    public int countTrapezoids(int[][] points) {
        Map<Integer, Long> yGroups = new HashMap<>();

        for (int[] point : points) {
            yGroups.put(point[1], yGroups.getOrDefault(point[1], 0L) + 1L);
        }

        long totalUniqueHorizontalLines = 0;
        long res = 0;
        for (long count : yGroups.values()) {
            long lines = count * (count - 1) / 2;
            res = (res + totalUniqueHorizontalLines * lines) % MOD;
            totalUniqueHorizontalLines = (totalUniqueHorizontalLines + lines) % MOD;
        }

        return (int) res;
    }
}