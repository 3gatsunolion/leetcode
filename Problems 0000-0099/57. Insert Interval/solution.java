class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        int index = 0;
        int[][] res = new int[n + 1][2];
        while (i < n && intervals[i][1] < newInterval[0]) {
            res[index++] = intervals[i++];
        }

        res[index++] = newInterval;

        while (i < n && intervals[i][0] <= newInterval[1]) {
            if (intervals[i][0] < newInterval[0]) {
                newInterval[0] = intervals[i][0];
            }
            if (intervals[i][1] > newInterval[1]) {
                newInterval[1] = intervals[i][1];
            }
            i++;
        }

        while (i < n) {
            res[index++] = intervals[i++];
        }

        return Arrays.copyOf(res, index);
    }
}