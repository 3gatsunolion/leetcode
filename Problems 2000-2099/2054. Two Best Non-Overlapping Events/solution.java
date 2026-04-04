class Solution {
    public int maxTwoEvents(int[][] events) {
        // int n = events.length;
        // int[][] eventTimes = new int[2 * n][3];

        // for (int i = 0; i < n; i++) {
        //     int start = events[i][0];
        //     int end = events[i][1];
        //     int val = events[i][2];
        //     eventTimes[2 * i][0] = start;
        //     // 1 -> isStart, 0 -> isEnd (we want in event of tie, for end
        //     // events to be before start events if the same time)
        //     eventTimes[2 * i][1] = 1;
        //     eventTimes[2 * i][2] = val;

        //     eventTimes[2 * i + 1][0] = end + 1; // inclusive
        //     eventTimes[2 * i + 1][1] = 0;
        //     eventTimes[2 * i + 1][2] = val;
        // }

        // Arrays.sort(eventTimes, (a, b) -> {
        //     if (a[0] == b[0]) {
        //         if (a[1] == b[1]) return a[2] - b[2];
        //         return a[1] - b[1];
        //     }
        //     return a[0] - b[0];
        // });

        // int maxEventValSoFar = 0;
        // int res = 0;
        // for (int[] event : eventTimes) {
        //     int time = event[0];
        //     int isStart = event[1];
        //     int val = event[2];

        //     if (isStart == 1) {
        //         res = Math.max(res, maxEventValSoFar + val);
        //     } else {
        //         maxEventValSoFar = Math.max(maxEventValSoFar, val);
        //     }
        // }

        // return res;

        // Arrays.sort(events, (a, b) -> a[1] - b[1]);

        // int n = events.length;
        // int[] maxEventValBefore = new int[n + 1];

        // for (int i = 0; i < n; i++) {
        //     maxEventValBefore[i + 1] = Math.max(maxEventValBefore[i], events[i][2]);
        // }

        // int res = 0;
        // for (int i = 0; i < n; i++) {
        //     int left = -1;
        //     int right = i - 1;

        //     while (left < right) {
        //         int mid = left + (right - left + 1) / 2;

        //         if (events[mid][1] < events[i][0]) {
        //             left = mid;
        //         } else {
        //             right = mid - 1;
        //         }
        //     }

        //     if (right >= 0) {
        //         res = Math.max(res, events[i][2] + maxEventValBefore[left + 1]);
        //     } else {
        //         res = Math.max(res, events[i][2]);
        //     }
        // }

        // return res;

        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        int n = events.length;
        int[] maxEventValAfter = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            maxEventValAfter[i] = Math.max(maxEventValAfter[i + 1], events[i][2]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (events[mid][0] > events[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int cand = events[i][2];
            if (left < n) {
                cand += maxEventValAfter[left];
            }

            res = Math.max(res, cand);
        }

        return res;
    }
}