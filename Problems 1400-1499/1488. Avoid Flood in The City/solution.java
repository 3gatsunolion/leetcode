class Solution {
    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> filledLakes = new HashMap<>();
        // Union Find approach
        int n = rains.length;
        int[] dryDate = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dryDate[i] = i;
        }

        int[] res = new int[n];
        for (int day = 0; day < n; day++) {
            // Dry day -> will be the parent of itself
            // and maybe previous fill dates
            if (rains[day] == 0) {
                res[day] = 1; // arbitrary
                continue;
            }
            int lake = rains[day];
            if (filledLakes.containsKey(lake)) {
                int prevFillDay = filledLakes.get(lake);
                int dry = find(dryDate, prevFillDay);
                if (dry >= day) {
                    return new int[0];
                }
                dryDate[dry] = find(dryDate, dry + 1);
                res[dry] = lake;
            }

            res[day] = -1;
            filledLakes.put(lake, day);
            // dry date needs to be at the very earliest day + 1
            dryDate[day] = day + 1;
        }
        return res;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // public int[] avoidFlood(int[] rains) {
    //     Map<Integer, Integer> filledLakes = new HashMap<>();
    //     TreeSet<Integer> dryDays = new TreeSet<>();
    //     int n = rains.length;
    //     int[] res = new int[n];
    //     for (int day = 0; day < n; day++) {
    //         int lake = rains[day];
    //         if (lake != 0) {
    //             if (filledLakes.containsKey(lake)) {
    //                 int prevFillDay = filledLakes.get(lake);
    //                 // Find the smallest element strictly 
    //                 // greater than prevFillDay
    //                 Integer dry = dryDays.higher(prevFillDay);
    //                 if (dry == null) {
    //                     return new int[] {};
    //                 }
    //                 res[dry] = lake;
    //                 dryDays.remove(dry);
    //             } else {
    //                 filledLakes.put(lake, day);
    //             }
    //             res[day] = -1;
    //             filledLakes.put(lake, day);
    //         } else {
    //             dryDays.add(day);
    //             res[day] = 1;
    //         }
    //     }
    //     return res;
    // }

    // public int[] avoidFlood(int[] rains) {
    //     Queue<Integer> lakesThatNeedToDry = new PriorityQueue<>();
    //     Map<Integer, LinkedList<Integer>> lakes = new HashMap<>();

    //     int n = rains.length;
    //     for (int day = 0; day < n; day++) {
    //         if (!lakes.containsKey(rains[day])) {
    //             lakes.put(rains[day], new LinkedList<>());
    //         }
    //         lakes.get(rains[day]).add(day);
    //     }

    //     int[] res = new int[n];
    //     for (int day = 0; day < n; day++) {
    //         if (!lakesThatNeedToDry.isEmpty() && lakesThatNeedToDry.peek() == day) {
    //             // The day where it rains in this lake
    //             // that needed to be dried out before this day
    //             // has arrived, which means it overflows
    //             return new int[] {};
    //         }
    //         int lake = rains[day];
    //         // We can dry a lake!
    //         if (lake == 0) {
    //             // Nothing to dry
    //             if (lakesThatNeedToDry.isEmpty()) {
    //                 // Can "dry" an arbitrary lake
    //                 res[day] = 1;
    //                 continue;
    //             }
    //             int dayLakeFillsAgain = lakesThatNeedToDry.poll();
    //             int lakeToDry = rains[dayLakeFillsAgain];
    //             res[day] = lakeToDry;
    //         } else {
    //             // Fill lake
    //             lakes.get(lake).pop();
    //             // If there's days later that will rain
    //             // in this lake again, then we need to add
    //             // to the heap, as we need to dry before then
    //             if (!lakes.get(lake).isEmpty()) {
    //                 int nextDay = lakes.get(lake).get(0);
    //                 lakesThatNeedToDry.add(nextDay);
    //             }
    //             res[day] = -1;
    //         }
    //     }
    //     return res;
    // }
}