class Solution {

    public int countTrapezoids(int[][] points) {
        // 1. Choose two lines with the same slope.
        // They form a trapezoid, unless they are collinear.
        // 2. To get UNIQUE trapezoids, be careful of double
        // counting parallelograms, since parallelograms
        // have two pairs of parallel lines, so when we iterate
        // over pairs of parallel lines, we may double count it

        int n = points.length;
        double inf = 1e9 + 7;
        Map<Double, List<Double>> slopeToIntercept = new HashMap<>();
        Map<Integer, List<Double>> midToSlope = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                int dx = x2 - x1;
                int dy = y2 - y1;

                if (x1 == x2 && y1 == y2) continue;

                double slope;
                double intercept;

                if (x1 == x2) {
                    slope = inf;
                    intercept = x1; // use x-intercept
                } else {
                    slope = (1.0 * dy) / dx;
                    // point–slope form
                    // y - y1 = m*(x - x1)
                    // Set x = 0 for y intercept
                    // y - y1 = -m*x1
                    // y = y1 - m * x1
                    // y = (y1 * dx - x1 * dy) / dx
                    // IMPORTANT: make sure to calculate
                    // numerator first as double then
                    // divide by dx or we get rounding point 
                    // error -> y1 - m * x1 -> m * x1 will
                    // be rounded then minused which will not
                    // give us the right value
                    intercept = (1.0 * (y1 * dx - x1 * dy)) / dx;
                }

                if (slope == -0.0) slope = 0.0;
                if (intercept == -0.0) intercept = 0.0;

                slopeToIntercept
                    .computeIfAbsent(slope, key -> new ArrayList<>())
                    .add(intercept);

                // Let's say this current line is a diagonal
                // of a trapezoid, if the trapezoid is
                // a parallelogram, the other diagonal will
                // intersect at each other's mid points of
                // the line -> use this to find parallelograms
                // Note: mid point is ((x1 + x2)/2, (y1 + y2)/2)
                // but we can just do ((x1 + x2), (y1 + y2))
                // Since constraints are –1000 <= xi, yi <= 1000
                // which means x1 + x2 and y1 + y2
                // is in [-2000, 2000]
                // We want 2^(n-1) - 1 >= 2000
                // n - 1 = 11 -> n = 12
                // Note: n - 1 is to accomodate negative numbers
                // so we need to add on one bit to make it
                // signed
                int mid = ((x1 + x2) << 12) + (y1 + y2);
                midToSlope
                    .computeIfAbsent(mid, key -> new ArrayList<>())
                    .add(slope);
            }
        }

        int res = 0;
        for (List<Double> intercepts : slopeToIntercept.values()) {
            // only 1 line with this slope so we can't form
            // trapezoid
            if (intercepts.size() == 1) continue;

            Map<Double, Integer> lines = new HashMap<>();
            for (double b : intercepts) {
                lines.put(b, lines.getOrDefault(b, 0) + 1);
            }

            int numParallelLinesSoFar = 0;
            for (int count : lines.values()) {
                res += numParallelLinesSoFar * count;
                numParallelLinesSoFar += count;
            }
        }

        for (List<Double> slopes : midToSlope.values()) {
            // only 1 diagonal with this midpoint, no need
            // worry about overcounting here
            if (slopes.size() == 1) continue;

            Map<Double, Integer> groupBySlope = new HashMap<>();
            for (double slope : slopes) {
                groupBySlope.put(slope, groupBySlope.getOrDefault(slope, 0) + 1);
            }

            int numDiagonals = 0;
            for (int count : groupBySlope.values()) {
                res -= count * numDiagonals;
                numDiagonals += count;

            }
        }

        return res;
    }
}