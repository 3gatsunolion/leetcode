class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // // Group by horizontal lines and vertical lines
        // Map<Integer, int[]> verticals = new HashMap<>();
        // Map<Integer, int[]> horizontals = new HashMap<>();

        // for (int[] building : buildings) {
        //     int x = building[0];
        //     int y = building[1];

        //     verticals.putIfAbsent(x, new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE});
        //     horizontals.putIfAbsent(y, new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE});

        //     verticals.get(x)[0] = Math.min(verticals.get(x)[0], y);
        //     verticals.get(x)[1] = Math.max(verticals.get(x)[1], y);

        //     horizontals.get(y)[0] = Math.min(horizontals.get(y)[0], x);
        //     horizontals.get(y)[1] = Math.max(horizontals.get(y)[1], x);
        // }

        // int count = 0;
        // for (int[] building : buildings) {
        //     int x = building[0];
        //     int y = building[1];

        //     int miny = verticals.get(x)[0];
        //     int maxy = verticals.get(x)[1];

        //     int minx = horizontals.get(y)[0];
        //     int maxx = horizontals.get(y)[1];

        //     if (x > minx && x < maxx && y > miny && y < maxy) count++;
        // }

        // return count;

        int[] minRows = new int[n + 1];
        int[] maxRows = new int[n + 1];
        int[] minCols = new int[n + 1];
        int[] maxCols = new int[n + 1];

        Arrays.fill(minRows, n + 1);
        Arrays.fill(minCols, n + 1);

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            minRows[y] = Math.min(minRows[y], x);
            maxRows[y] = Math.max(maxRows[y], x);

            minCols[x] = Math.min(minCols[x], y);
            maxCols[x] = Math.max(maxCols[x], y);
        }
        
        int covered = 0;
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            if (x > minRows[y] && x < maxRows[y] && y > minCols[x] && y < maxCols[x]) {
                covered++;
            }
        }

        return covered;
    }
}