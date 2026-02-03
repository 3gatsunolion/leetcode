class Solution {
    // public long minimumCost(int[] nums, int k, int dist) {
    //     // Sliding window

    //     Queue<int[]> usedQ = new PriorityQueue<>((a, b) -> {
    //         if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
    //         return Integer.compare(b[0], a[0]);
    //     });
    //     Queue<int[]> unusedQ = new PriorityQueue<>((a, b) -> {
    //         if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
    //         return Integer.compare(a[0], b[0]);
    //     });
    //     Set<Integer> used = new HashSet<>();

    //     long sum = 0;
    //     long minSum = Long.MAX_VALUE;
    //     for (int right = 1; right < nums.length; right++) {
    //         // Need to remove now
    //         int left = right - dist - 1;

    //         if (left > 0 && used.contains(left)) {
    //             sum -= nums[left];
    //             used.remove(left);

    //             while (!unusedQ.isEmpty() && unusedQ.peek()[1] < left) {
    //                 unusedQ.poll();
    //             }

    //             if (!unusedQ.isEmpty()) {
    //                 int[] addToUsed = unusedQ.poll();
    //                 sum += addToUsed[0];
    //                 used.add(addToUsed[1]);
    //                 usedQ.add(addToUsed);
    //             }
    //         }

    //         if (used.size() < k - 1) {
    //             used.add(right);
    //             usedQ.offer(new int[] {nums[right], right});
    //             sum += nums[right];
    //         } else {
    //             while (!usedQ.isEmpty() && !used.contains(usedQ.peek()[1])) {
    //                 usedQ.poll();
    //             }

    //             if (usedQ.peek()[0] > nums[right]) {
    //                 int[] addToUnused = usedQ.poll();
    //                 sum -= addToUnused[0];
    //                 used.remove(addToUnused[1]);
    //                 unusedQ.offer(addToUnused);
    //                 usedQ.offer(new int[] {nums[right], right});
    //                 used.add(right);
    //                 sum += nums[right];
    //             } else {
    //                 unusedQ.offer(new int[] {nums[right], right});
    //             }
    //         }

    //         if (left >= 0) {
    //             minSum = Math.min(minSum, sum);
    //         }
    //     }
    //     return minSum + nums[0];
    // }

    public long minimumCost(int[] nums, int k, int dist) {
        Queue<Integer> used = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        Queue<Integer> unused = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        Map<Integer, Integer> toRemove = new HashMap<>();

        long sum = 0;
        long minSum = Long.MAX_VALUE;
        int numUsed = 0;
        for (int right = 1; right < nums.length; right++) {
            // Need to remove now
            int left = right - dist - 1;

            if (left > 0) {
                if (!used.isEmpty() && nums[left] <= used.peek()) {
                    numUsed--;
                    sum -= nums[left];
                    if (used.peek() == nums[left]) {
                        used.poll();
                    } else {
                        toRemove.put(nums[left], toRemove.getOrDefault(nums[left], 0) + 1);
                    }
                } else if (!unused.isEmpty() && nums[left] == unused.peek()) {
                    unused.poll();
                } else {
                    toRemove.put(nums[left], toRemove.getOrDefault(nums[left], 0) + 1);
                }
            }

            if (right < k || nums[right] < used.peek()) {
                used.offer(nums[right]);
                sum += nums[right];
                numUsed++;
            } else {
                unused.offer(nums[right]);
            }

            if (right > k - 1) {
                // Need to add one more
                if (numUsed < k - 1) {
                    int num = unused.poll();
                    sum += num;
                    used.offer(num);
                    numUsed++;
                } else if (numUsed > k - 1) {
                    int num = used.poll();
                    sum -= num;
                    numUsed--;
                    unused.offer(num);
                }
            }

            // Cleanup
            // NOTE: It doesn't matter that we only have one toRemove
            // map and we don't have to worry if we're deleting the value
            // from the "wrong" heap. Why? We know used <= unused
            // for all values. If a val is in both used and unused,
            // then it's the largest in used and the smallest in unused
            // which means the value will always get popped from used
            // and it will not be incremented in toRemove to begin with
            // So it's safe to say we're always deleting form the right heap
            while (!used.isEmpty() && toRemove.getOrDefault(used.peek(), 0) > 0) {
                int num = used.poll();
                toRemove.put(num, toRemove.get(num) - 1);
            }

            while (!unused.isEmpty() && toRemove.getOrDefault(unused.peek(), 0) > 0) {
                int num = unused.poll();
                toRemove.merge(num, -1, Integer::sum);
            }

            if (left >= 0) {
                minSum = Math.min(minSum, sum);
            }
        }
        return minSum + nums[0];
    }
}