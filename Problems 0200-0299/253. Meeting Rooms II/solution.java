/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    // public int minMeetingRooms(List<Interval> intervals) {
    //     int n = intervals.size();
    //     int[][] events = new int[2 * n][];
    //     for (int i = 0; i < n; i++) {
    //         int start = intervals.get(i).start;
    //         int end = intervals.get(i).end;
    //         events[2 * i] = new int[] {start, 1};
    //         events[2 * i + 1] = new int[] {end, -1};
    //     }

    //     Arrays.sort(events, (a, b) -> {
    //         if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
    //         return Integer.compare(a[0], b[0]);
    //     });

    //     int numDaysNeeded = 0;
    //     int currNumRooms = 0;
    //     for (int[] event : events) {
    //         currNumRooms += event[1];
    //         numDaysNeeded = Math.max(numDaysNeeded, currNumRooms);
    //     }

    //     return numDaysNeeded;
    // }

    // public int minMeetingRooms(List<Interval> intervals) {
    //     int n = intervals.size();
    //     int[] starts = new int[n];
    //     int[] ends = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         int start = intervals.get(i).start;
    //         int end = intervals.get(i).end;
    //         starts[i] = start;
    //         ends[i] = end;
    //     }

    //     Arrays.sort(starts);
    //     Arrays.sort(ends);

    //     int s = 0;
    //     int e = 0;
    //     int numDaysNeeded = 0;
    //     int currNumRooms = 0;
    //     while (s < n) {
    //         if (starts[s] < ends[e]) {
    //             s++;
    //             currNumRooms++;
    //             numDaysNeeded = Math.max(numDaysNeeded, currNumRooms);
    //         } else {
    //             e++;
    //             currNumRooms--;
    //         }
    //     }

    //     return numDaysNeeded;
    // }

    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        Queue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            // A meeting ended, so we can pop that and use that room
            // for this meeting
            if (!minHeap.isEmpty() && minHeap.peek() <= interval.start) {
                minHeap.poll();
            }
            minHeap.offer(interval.end);
        }

        return minHeap.size();
    }
}
