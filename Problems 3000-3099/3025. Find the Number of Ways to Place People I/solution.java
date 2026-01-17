class Solution {
    public int numberOfPairs(int[][] points) {
        // Key: points are distinct, otherwise those points
        // cannot be included
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int n = points.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            long maxY = Long.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                // points[i] cannot be top left of points[j]
                if (points[j][1] > points[i][1]) continue;
                // will overlap
                if (points[j][1] <= maxY) continue;

                maxY = points[j][1];
                count++;
            }
        }
        return count;
    }
}