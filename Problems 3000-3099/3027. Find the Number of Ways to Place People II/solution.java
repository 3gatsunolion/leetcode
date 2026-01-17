class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int n = points.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int[] tl = points[i];
            long maxY = Long.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                int[] br = points[j];
                if (br[1] <= tl[1] && br[1] > maxY) {
                    maxY = br[1];
                    count++;
                }
                if (maxY == tl[1]) break;
            }
        }
        return count;
    }
}