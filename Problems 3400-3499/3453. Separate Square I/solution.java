class Solution {
    public double separateSquares(int[][] squares) {
        double lo = Double.MAX_VALUE;
        double hi = Double.MIN_VALUE;

        double totalArea = 0;
        for (int[] square : squares) {
            int x = square[0], y = square[1], l = square[2];
            lo = Math.min(lo, y);
            hi = Math.max(hi, y + l);
            totalArea += (double) l * l;
        }

        double eps = Math.pow(10, -5);
        while (hi - lo > eps) {
            double mid = lo + (hi - lo) / 2.0;

            double lowerArea = 0.0;
            for (int[] square : squares) {
                double y = square[1];
                double l = square[2];
                double top = y + l;

                if (top <= mid) {
                    lowerArea += l * l;
                } else {
                    if (y < mid) {
                        double hLow = mid - y;
                        lowerArea += hLow * l;
                    }
                }
            }

            double higherArea = totalArea - lowerArea;
            if (higherArea <= lowerArea) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return lo;
    }
}