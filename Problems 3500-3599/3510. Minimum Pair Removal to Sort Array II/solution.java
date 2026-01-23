class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        long[] vals = new long[n];
        for (int i = 0; i < n; i++) {
            vals[i] = nums[i];
        }

        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
        }

        // Store [adj sum, leftmost index]
        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });

        int badCount = 0;
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[] {vals[i] + vals[i + 1], i});
            if (vals[i] > vals[i + 1]) badCount++;
        }

        int numOp = 0;
        while (badCount > 0 && !pq.isEmpty()) {
            long[] top = pq.poll();
            long minSum = top[0];
            int i = (int) top[1];
            int j = right[i];

            // Ignore stale
            // IMPORTANT: j >= n -> it may seem like this could never happen
            // but it could happen that we've deleted everything after i
            // before this iteration, so i and j pair are stale now
            if (j >= n || left[j] != i || vals[i] + vals[j] != minSum) continue;

            // Update badCount
            if (vals[i] > vals[j]) badCount--;
            int prev = left[i];
            // Decrement badCount here, if after this operation vals[prev]
            // is still > vals[i], then we will increment badCount again
            if (prev != -1 && vals[prev] > vals[i]) badCount--;
            int next = right[j];
            if (next != n && vals[j] > vals[next]) badCount--;

            // Merge
            vals[i] = minSum;
            right[i] = next;
            if (next != n) left[next] = i;

            numOp++;
            
            // Update badCount
            if (prev != -1) {
                if (vals[prev] > vals[i]) badCount++;
                pq.offer(new long[] {vals[prev] + vals[i], prev});
            }
            if (next != n) {
                if (vals[i] > vals[next]) badCount++;
                pq.offer(new long[] {vals[i] + vals[next], i});
            }
        }

        return numOp;
    }
}