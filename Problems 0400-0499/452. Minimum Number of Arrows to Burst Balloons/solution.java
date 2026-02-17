class Solution {
    public int findMinArrowShots(int[][] points) {
        // Note: p1[1] - p2[1] -> integer overflow
        // Consider comparing Integer.MAX_VALUE (2147483647) and -1.
        // If the compare method returns Integer.MAX_VALUE - (-1),
        // which is 2147483647 + 1, this calculation overflows and 
        // results in Integer.MIN_VALUE (-2147483648).
        Arrays.sort(points, (p1, p2) -> {
            if (p1[1] < p2[1]) {
                return -1;
            } else if (p1[1] == p2[1]) {
                return 0;
            } else {
                return 1;
            }
        });

        long arrow = Long.MIN_VALUE;

        int count = 0;
        for (int[] point : points) {
            if (point[0] > arrow) {
                arrow = point[1];
                count++;
            }
        }
        return count;
    }
}