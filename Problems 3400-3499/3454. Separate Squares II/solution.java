class SegmentTree {
    // All relevant x coordinates (either start or end of square, or both)
    // ordered
    private int[] xs;
    // widthCovered[i] = width covered in interval i
    private int[] widthCovered;
    // count[i] = number of squares covering this interval i
    private int[] count;
    private int n;
    public SegmentTree(int[] xs) {
        this.xs = xs;
        int n = xs.length;
        widthCovered = new int[4 * n];
        count = new int[4 * n];
    }

    private void update(
        int node,
        int left,
        int right,
        int qLeft,
        int qRight,
        int delta) {
        if (xs[left] >= qRight || xs[right] <= qLeft) return;
        if (xs[left] >= qLeft && xs[right] <= qRight) {
            count[node] += delta;
        } else {
            int mid = left + (right - left) / 2;
            update(node * 2 + 1, left, mid, qLeft, qRight, delta);
            // NOTE: Not mid + 1 to right because we want an interval
            // if we go mid + 1, we're skipping interval from
            // xs[mid] to xs[mid + 1]
            update(node * 2 + 2, mid, right, qLeft, qRight, delta);
        }

        if (count[node] > 0) {
            widthCovered[node] = xs[right] - xs[left];
        } else {
            // Leaf node/Base case:
            // Smallest interval is between two consecutive 
            // x-coordinates:
            if (right - left == 1) {
                widthCovered[node] = 0;
            } else {
                widthCovered[node] = widthCovered[node * 2 + 1] + widthCovered[node * 2 + 2];
            }
        }
    }

    public void update(int qLeft, int qRight, int delta) {
        update(0, 0, xs.length - 1, qLeft, qRight, delta);
    }

    public int covered() {
        return widthCovered[0];
    }
}

class Solution {
    // public double separateSquares(int[][] squares) {
    //     int n = squares.length;
    //     if (n == 0) return -1;

    //     int[] xs = new int[2 * n];
    //     int[][] events = new int[2 * n][4];
    //     for (int i = 0; i < n; i++) {
    //         int[] square = squares[i];
    //         int x = square[0], y = square[1], l = square[2];

    //         int start = i * 2, end = i * 2 + 1;
    //         xs[start] = x;
    //         xs[end] = x + l;

    //         events[start][0] = y;
    //         events[start][1] = 1; // 1 -> start
    //         events[start][2] = x;
    //         events[start][3] = x + l;

    //         events[end][0] = y + l;
    //         events[end][1] = -1; // -1 -> end
    //         events[end][2] = x;
    //         events[end][3] = x + l;
    //     }

    //     Arrays.sort(xs);
    //     // Get rid of duplicate x values
    //     int numX = 1;
    //     for (int i = 1; i < xs.length; i++) {
    //         if (xs[i] != xs[i - 1]) {
    //             xs[numX++] = xs[i];
    //         }
    //     }

    //     xs = Arrays.copyOf(xs, numX);
    //     Arrays.sort(events, (a, b) -> {
    //         if (a[0] == b[0]) {
    //             return a[1] - b[1];
    //         }
    //         return Integer.compare(a[0], b[0]);
    //     });

    //     SegmentTree tree = new SegmentTree(xs);
    //     int prevY = 0;
    //     long totalArea = 0;
    //     // widths[i]: from index i to index i + 1, how much width
    //     // is covered?
    //     int[] widths = new int[2 * n];
    //     long[] prefixAreas = new long[2 * n];
    //     for (int i = 0; i < events.length; i++) {
    //         int[] event = events[i];
    //         int y = event[0], delta = event[1];
    //         int xStart = event[2];
    //         int xEnd = event[3];

    //         int widthCovered = tree.covered();
    //         totalArea += (long) (widthCovered) * (y - prevY);

    //         tree.update(xStart, xEnd, delta);

    //         prefixAreas[i] = totalArea;
    //         widths[i] = tree.covered();

    //         prevY = y;
    //     }

    //     // We want to find the FIRST index i such that prefixAreas[i] >=
    //     // (totalArea) / 2 -> (totalArea + 1) / 2 is so odd number
    //     // will result in > (totalArea) / 2
    //     // Since we know this is the first such index with this value,
    //     // the value previous to this, prefixAreas[i - 1], will be a different
    //     // value, and therefore widths[i - 1] will NOT be 0
    //     long target = (long) (totalArea + 1) / 2;
    //     int i = binarySearch(prefixAreas, target);

    //     // prefixAreas[0] is always 0, therefore return 0
    //     if (i == 0) return 0;

    //     int y = events[i - 1][0];
    //     int widthCovered = widths[i - 1];
    //     long areaBefore = prefixAreas[i - 1];
    //     double remaining = (totalArea / 2.0) - areaBefore;

    //     return y + remaining * 1.0 / widthCovered;
    // }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        if (n == 0) return -1;

        int[] xs = new int[2 * n];
        int[][] events = new int[2 * n][4];
        for (int i = 0; i < n; i++) {
            int[] square = squares[i];
            int x = square[0], y = square[1], l = square[2];

            int start = i * 2, end = i * 2 + 1;
            xs[start] = x;
            xs[end] = x + l;

            events[start][0] = y;
            events[start][1] = 1; // 1 -> start
            events[start][2] = x;
            events[start][3] = x + l;

            events[end][0] = y + l;
            events[end][1] = -1; // -1 -> end
            events[end][2] = x;
            events[end][3] = x + l;
        }

        Arrays.sort(xs);
        // Get rid of duplicate x values
        int numX = 1;
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] != xs[i - 1]) {
                xs[numX++] = xs[i];
            }
        }

        xs = Arrays.copyOf(xs, numX);
        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return Integer.compare(a[0], b[0]);
        });

        SegmentTree tree = new SegmentTree(xs);
        int prevY = 0;
        int widthCovered = 0;
        long totalArea = 0;

        int[] startY = new int[events.length];
        int[] endY = new int[events.length];
        int[] widths = new int[events.length];
        int k = 0;
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            int y = event[0], delta = event[1];
            int xStart = event[2];
            int xEnd = event[3];

            int dy = y - prevY;
            if (dy > 0 && widthCovered > 0) {
                totalArea += (long) widthCovered * dy;
                startY[k] = prevY;
                endY[k] = y;
                widths[k] = widthCovered;
                k++;
            }

            tree.update(xStart, xEnd, delta);
            widthCovered = tree.covered();
            prevY = y;
        }

        if (totalArea == 0) return 0;

        double target = totalArea / 2.0;
        long currArea = 0;
        for (int i = 0; i < k; i++) {
            long areaToAdd = (long) (endY[i] - startY[i]) * widths[i];
            if (currArea + areaToAdd < target) {
                currArea += areaToAdd;
            } else {
                double remaining = target - currArea;
                return startY[i] + (remaining) / widths[i];
            }
        }
        return -1;
    }

    // lower bound since we want lowest y
    private int binarySearch(long[] nums, long target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}