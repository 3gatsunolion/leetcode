class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            int xDiff = Math.abs(points[i][0] - points[i - 1][0]);
            int yDiff = Math.abs(points[i][1] - points[i - 1][1]);

            // Need: min + (max - min) = max
            // Whatever will be minimum will be eventually covered by maximum by going diagonally
            int max = Math.max(xDiff, yDiff);
            res += max;
        }
        
        return res;
    }
}