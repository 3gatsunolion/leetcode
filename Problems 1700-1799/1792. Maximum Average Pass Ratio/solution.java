class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                if (a[0] < b[0]) {
                    return 1;
                } else if (a[0] > b[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (int[] c : classes) {
            double pass = c[0];
            double total = c[1];
            double add = (pass + 1.0) / (total + 1.0) - pass / total;
            maxHeap.offer(new double[] { add, pass, total });
        }

        for (int i = 0; i < extraStudents; i++) {
            double[] c = maxHeap.poll();
            
            double pass = c[1] + 1;
            double total = c[2] + 1;
            double add = (pass + 1.0) / (total + 1.0) - pass / total;

            maxHeap.offer(new double[] { add, pass, total });
        }

        double res = 0;
        while (!maxHeap.isEmpty()) {
            double[] c = maxHeap.poll();
            res += c[1] / c[2];
        }

        return res / classes.length;
    }
}